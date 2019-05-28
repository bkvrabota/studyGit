using System;
using System.Collections.Generic;
using UnityEngine;
using System.Linq;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class GameController : MonoBehaviour {

    public GameObject LetterBlock;
    public GameObject EmptyBlock;
    public GameObject StartBlock;
    public GameObject FinishBlock;
    public GameObject CellBlock;
    public int previousCellX;
    public int previousCellY;
    public bool afterCheck = false;
    public Text Score;
    public Text CheckRes;
    public LetterPanel letterPanel;
    public List<TextAsset> confLVL;
    public GameObject FWindow;
    public int fieldSize;
    public FingerMove fingerMove;
    public GameObject BackButton;
    int cellx;
    int celly;
    private List<List<GameObject>> words;
    private const int FIELD_SIZE = 50;
    private List<List<char>> rawConf;    
    private int iS;
    private int kS;
    private int iF;
    private int kF;
    private List<GameObject> wordsCheckedTrue = new List<GameObject>();
    private List<GameObject> wordsCheckedClick = new List<GameObject>();
    private int? cellxFirst = null;
    private int? cellyFirst = null;
    private bool Clicked;
    private int? cellxS = null;
    private int? cellyS = null;    
    private bool testCheckRes;
    private bool SButtonOff;    
    private bool firstCheck = false;
    private bool firstCheckWord = false;
    private int kSS;
    private DictManager dict;    
    private List<TextAsset> confAssets;    
    private int rawCount;    
    string[] stringSeparators;


    private void Awake()
    {
        dict = DictManager.GetManager();
        LoadConfig();
    }

    void Start() {
        words = new List<List<GameObject>>();
        fieldSize = rawConf.Count * 80;
        //Debug.Log("FIELD_SIZE = " + fieldSize/80);
        
        for (int j = 0; j < rawConf.Count; j++)           
        {
            words.Add(new List<GameObject>());
            for (int i = rawConf[j].Count - 1; i >= 0; i--)
            {
                SpawnCell(j, i);
                SpawnCell(0, rawConf.Count - 1);
                if (rawConf[i][j] == '1') {
                    SpawnEmpty(j, i);
                } else if (rawConf[i][j] == 'S') {
                    SpawnStart(j, i);
                } else if (rawConf[i][j] == 'F') {
                    SpawnFinish(j, i);
                } else {                    
                    words[j].Add(null);
                }
                rawCount = rawConf[j].Count - 1;                
            }
        }        
        ChangeFSize();
        Create_OnClick();
        LocButton();
    }
    
    private void LoadConfig()
    {
        var conf_Path = "config/level " + (dict.Lvl + 1);
        TextAsset confAsset = (TextAsset)Resources.Load(conf_Path, typeof(TextAsset));
        rawConf = new List<List<char>>();

        if (dict.isMacOsX)
        {
            stringSeparators = new string[] { "\n" };
        }
        else {
            stringSeparators = new string[] { "\r\n" };
        }

        var rawList = confAsset.text.Split(stringSeparators, StringSplitOptions.None);
        
        foreach (var item2 in rawList)
        {
            char[] fieldRow = item2.ToCharArray();
            rawConf.Add(fieldRow.ToList());
        }
    }

    private void ChangeFSize()
    {
        var gContr = GameObject.FindGameObjectWithTag("GameController");
        gContr.GetComponent<SpriteRenderer>().size = new Vector2(fieldSize, fieldSize);
    }

    void SpawnStart(int i, int k)
    {
        iS = i;
        kS = k;        
        var go = Instantiate(StartBlock, new Vector3(0, 0, 0), Quaternion.identity);
        go.transform.SetParent(transform, false);
        go.transform.localPosition = new Vector3(((iS * 80 - fieldSize / 2) + 80 / 2), ((fieldSize / 2 - kS * 80) - 80 / 2), 10f);
        words[iS].Add(go);
    }

    void SpawnFinish(int i, int k)
    {
        iF = i;
        kF = k;
        var go = Instantiate(FinishBlock, new Vector3(0, 0, 0), Quaternion.identity);
        go.transform.SetParent(transform, false);
        go.transform.localPosition = new Vector3(((iF * 80 - fieldSize / 2) + 80 / 2), ((fieldSize / 2 - kF * 80) - 80 / 2), 10f);
        words[iF].Add(go);
    }

    void SpawnEmpty(int iE, int kE)
    {
        var go = Instantiate(EmptyBlock, new Vector3(0, 0, 0), Quaternion.identity);
        go.transform.SetParent(transform, false);
        go.transform.localPosition = new Vector3(((iE * 80 - fieldSize / 2) + 80 / 2), ((fieldSize / 2 - kE * 80) - 80 / 2), 10f);
        words[iE].Add(go);
    }

    void SpawnCell(int iС, int kС)
    {
        var go = Instantiate(CellBlock, new Vector3(0, 0, 0), Quaternion.identity);
        go.transform.SetParent(transform, false);
        go.transform.localPosition = new Vector3(((iС * 80 - fieldSize / 2) + 80 / 2), ((fieldSize / 2 - kС * 80) - 80 / 2), 10f);
    }

    public GameObject CreateLetter(string letter) {
        var go = Instantiate(LetterBlock, new Vector3(0, 0, 0), Quaternion.identity);
        var LPanel = GameObject.FindGameObjectWithTag("LPanel");
        go.transform.SetParent(LPanel.transform, false);
        go.transform.localPosition = new Vector3(0, 0, 10f);
        go.GetComponent<Letter>().SetXY(0, 0);
        go.GetComponent<Letter>().CreateLetter(letter);
        letterPanel.LPanel[0][0] = go;

        return go;
    }

    void LocButton() { 
        var BButton = "";
        var FWindowLoc = "";
        if (dict.locale == "ru") {            
            BButton = "Назад";
            FWindowLoc = "УРОВЕНЬ ПРОЙДЕН!" + "\r\n" + "ПОЗДРАВЛЯЕМ!";
        } else if (dict.locale == "en") {            
            BButton = "Back";
            FWindowLoc = "LEVEL COMPLITE!" + "\r\n" + "CONGRATULATIONS!";
        }        
        BackButton.GetComponentInChildren<Text>().text = BButton;
        FWindow.GetComponentInChildren<Text>().text = FWindowLoc;
    }

    public void TryToLocateBlock(GameObject block)
    {
        int tileHeight = 80;
        int fieldWidth = fieldSize;
        var gContr = GameObject.FindGameObjectWithTag("GameController");        
        block.transform.SetParent(gContr.transform);
        var pos = new Vector2(
                        fieldWidth / 2 + block.transform.localPosition.x,
                        fieldWidth / 2 + block.transform.localPosition.y);
        cellx = (int)Mathf.Abs(Mathf.Round((pos.x - tileHeight / 2) / tileHeight));
        celly = (int)Mathf.Abs(Mathf.Round((pos.y - tileHeight / 2) / tileHeight));
        var letter = block.GetComponent<Letter>();
        var checkLocation = block.transform.localPosition;

        if (letter.moveIt == true && 
            ((-fieldSize / 2 <= checkLocation.x && checkLocation.x <= fieldSize / 2) &&
             (-fieldSize / 2 <= checkLocation.y && checkLocation.y <= fieldSize / 2)) && words[cellx][celly] == null ) {

            letter.locateToField = true;
            letterPanel.LPanel[0][0] = null;
            letter.SetXY(cellx, celly);
            words[cellx][celly] = block;
            letter.moveIt = false;
            block.transform.localPosition = new Vector3((cellx * tileHeight - fieldWidth / 2) + tileHeight / 2,
            (celly * tileHeight - fieldWidth / 2) + tileHeight / 2, 10f);
            block.GetComponent<SpriteRenderer>().sortingLayerName = "Main";
            Create_OnClick();            
        }
        else {
            var lPanel = GameObject.FindGameObjectWithTag("LPanel");
            block.transform.SetParent(lPanel.transform);
            block.transform.localPosition = new Vector3(0, 0, 10f);
        }
    }

    public void Create_OnClick()
    {
        {
            if (afterCheck)
            {
                GetComponent<FingerMove>().userWord = null;
            }
            CreateLetter(dict.GetNextLetter());
            afterCheck = false;            
        }
        if (!SButtonOff)
        {
            CloseButton();
        }
    }

    public void CloseButton()
    {
        var SButton = GameObject.FindGameObjectWithTag("SButton");
        SButton.gameObject.SetActive(false);
        SButtonOff = true;
    }

    public void CheckedWord(GameObject newLetter)
    {
        var ltrBlock = newLetter.GetComponent<Letter>();
        cellx = ltrBlock.cellx;
        celly = ltrBlock.celly;
        
        if (ltrBlock.locateToField 
            && ltrBlock.clickIt == true)
        {
            wordsCheckedClick.Add(newLetter);
            if (cellxS == null && cellyS == null)
            {
                cellxS = cellx;
                if (!firstCheck)
                {
                    cellyS = rawCount - celly;
                    firstCheck = true;
                } else
                {
                    cellyS = celly;
                }
            }
            
            if ((cellxFirst == null && cellyFirst == null) || ((cellx == cellxFirst + 1 && celly == cellyFirst) 
                || (cellx == cellxFirst - 1 && celly == cellyFirst) || (celly == cellyFirst + 1 && cellx == cellxFirst) 
                || (celly == cellyFirst - 1 && cellx == cellxFirst)))
            {
                cellxFirst = cellx;
                cellyFirst = celly;                
                wordsCheckedTrue.Add(newLetter);
                GetComponent<FingerMove>().userWord = GetComponent<FingerMove>().userWord + ltrBlock.letterInBlock;
                var clickIt = ltrBlock.clickIt = false;
                Clicked = clickIt;
            } else {
                ltrBlock.clickIt = true;
            }

            var userWord = GetComponent<FingerMove>().userWord;
            CheckRes.text = userWord + ": ";
        }
    }

    public void Check_OnClick()
    {
        if (!Clicked && (cellx != 0 || celly != 0))
        {            
            afterCheck = false;
            var userWord = GetComponent<FingerMove>().userWord;
            var truth = dict.CheckWord(userWord);
            afterCheck = true;

            if (afterCheck && truth)
            {
                for (int i = 0; i < wordsCheckedTrue.Count; i++)
                {
                    Destroy(wordsCheckedTrue[i]);
                }
            }
            else if (!truth)
            {
                for (int i = 0; i < wordsCheckedClick.Count; i++)
                {
                    wordsCheckedClick[i].GetComponent<Letter>().clickIt = true;
                }
            }  
            
            if (truth && ((cellxS == iS + 1 && cellyS == kS) || (cellyS == kS + 1 && cellxS == iS) 
                || (cellxS == iS - 1 && cellyS == kS) || (cellyS == kS - 1 && cellxS == iS)))
            {
                var objS = GameObject.FindGameObjectWithTag("StartB");
                Destroy(objS);
                iS = cellx;
                kS = celly;
                var go = Instantiate(StartBlock, new Vector3(0, 0, 0), Quaternion.identity);
                go.transform.SetParent(transform, false);
                go.transform.localPosition = new Vector3((iS * 80 - fieldSize / 2) + 80 / 2, (kS * 80 - fieldSize / 2) + 80 / 2, 10f);
                words[iS][kS] = go;
                firstCheckWord = true;
                kSS = rawCount - celly;
                if ((iS == iF - 1 && kSS == kF) || (kSS == kF - 1 && iS == iF) || (iS == iF + 1 && kSS == kF) || (kSS == kF + 1 && iS == iF)) {
                    dict.CheckLVL[dict.Lvl] = true;
                    FWindow.SetActive(true);
                    dict.ToggleBoxCheck();
                }
            } else if (!firstCheckWord) { firstCheck = false; }

            if (afterCheck)
            {
                GetComponent<FingerMove>().userWord = null;
            }

            cellxS = null;
            cellyS = null;
            testCheckRes = truth;
            CheckRes.text = userWord + ": " + testCheckRes;
            if (truth)
            {
                dict.lastScore = dict.lastScore + wordsCheckedTrue.Count;
            }
            Score.text = "S: " + dict.lastScore;
            wordsCheckedTrue.Clear();
            wordsCheckedClick.Clear();
            cellxFirst = null;
            cellyFirst = null;
            PlayerPrefs.SetInt("Last Score", dict.lastScore);

            if (dict.lastScore > dict.topScore)
            {
                dict.topScore = dict.lastScore;
                PlayerPrefs.SetInt("Top Score", dict.topScore);
            }
        }
    }

    public void Back()
    {
        dict.lastScore = 0;
        FWindow.SetActive(false);
        SceneManager.LoadScene("MenuScene", LoadSceneMode.Single);        
    }
}

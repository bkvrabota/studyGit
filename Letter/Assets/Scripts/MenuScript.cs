using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class MenuScript : MonoBehaviour {

    public Text LScore;
    public Text TScore;    
    public List<GameObject> LvlButton;
    public List<GameObject> ToggleBox;
    public GameObject ResetButton;
    public GameObject BackButton;
    private DictManager dict;

    void Start () {
        dict = DictManager.GetManager();
        LastScore();
        TopScore();

        var TBCSave = PlayerPrefs.GetString("Toggle Box", "00000").ToCharArray();
        for (int i = 0; i < TBCSave.Length; i++)
        {
            if (TBCSave[i] == '1')
            {
                ToggleBox[i].GetComponent<Toggle>().isOn = true;
            }
        }
        LocButton();
    }

    void LastScore()
    {
        var LastScore = PlayerPrefs.GetInt("Last Score", 0);        
        var LScoreText = "";
        if (dict.locale == "ru") {
            LScoreText = "Последний результат: ";
        } else if (dict.locale == "en") {
            LScoreText = "Last score: ";
        }
        LScore.text = LScoreText + LastScore.ToString();
    }

    void TopScore()
    {
        var TopScore = PlayerPrefs.GetInt("Top Score", dict.topScore);
        var TScoreText = "";
        if (dict.locale == "ru") {
            TScoreText = "Лучший результат: ";
        }
        else if (dict.locale == "en") {
            TScoreText = "Top score: ";
        }
        TScore.text = TScoreText + TopScore.ToString();
    }

    void LocButton() { 
        var LButton = "";
        var RButton = "";
        var BButton = "";
        if (dict.locale == "ru") {
            LButton = "Уровень ";
            RButton = "Сброс результата";
            BButton = "Назад";
        } else if (dict.locale == "en") {
            LButton = "Level ";
            RButton = "Reset score";
            BButton = "Back";
        }
        for (int i = 0; i < LvlButton.Count; i++) {            
            LvlButton[i].GetComponentInChildren<Text>().text = LButton + (i + 1);
        }
        ResetButton.GetComponentInChildren<Text>().text = RButton;
        BackButton.GetComponentInChildren<Text>().text = BButton;
    }

    //BUTTONS:
    public void ResetScore()
    {
        for (int i = 0; i < 5; i++)
        {
            dict.CheckLVL[i] = false;
            ToggleBox[i].GetComponent<Toggle>().isOn = false;
        }
        PlayerPrefs.SetString("Toggle Box", "00000");

        dict.lastScore = 0;
        PlayerPrefs.SetInt("Last Score", dict.lastScore);
        LastScore();

        dict.topScore = 0;
        PlayerPrefs.SetInt("Top Score", dict.topScore);
        TopScore();
    }

    public void Back()
    {
        SceneManager.LoadScene("StartScene", LoadSceneMode.Single);
    }

    public void GoToLVL1()
    {
        SceneManager.LoadScene("GameScene", LoadSceneMode.Single);
        dict.Lvl = 0;
        dict.LoadWord();
    }
    public void GoToLVL2()
    {
        SceneManager.LoadScene("GameScene", LoadSceneMode.Single);
        dict.Lvl = 1;
        dict.LoadWord();
    }
    public void GoToLVL3()
    {
        SceneManager.LoadScene("GameScene", LoadSceneMode.Single);
        dict.Lvl = 2;
        dict.LoadWord();
    }
    public void GoToLVL4()
    {
        SceneManager.LoadScene("GameScene", LoadSceneMode.Single);
        dict.Lvl = 3;
        dict.LoadWord();
    }
    public void GoToLVL5()
    {
        SceneManager.LoadScene("GameScene", LoadSceneMode.Single);
        dict.Lvl = 4;
        dict.LoadWord();
    }
}

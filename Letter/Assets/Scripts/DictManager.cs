using System.Collections.Generic;
using System.IO;
using UnityEngine;
using UnityEngine.SceneManagement;


public class DictManager : MonoBehaviour {

    public static DictManager GetManager() {
        var root = GameObject.FindGameObjectWithTag("Root");
        DictManager manager = null;
        if (root != null) {
            manager = root.GetComponent<DictManager>();
        }        
        return manager;
    }

    private int RU_MAX_FILE = 26;
    private int EN_MAX_FILE = 12;
    private int lettersMinimumBuffer = 15;
    private string lettersBuffer = "";
    private List<List<string>> dictionary;
    private List<List<string>> dictionary_ru; 
    private List<List<string>> dictionaryNoun_ru; 
    private List<List<string>> dictionary_en; 
    private string ToggleBoxCheckSave;
    private string[] stringSeparators;
    private string[] stringSeparators_ru;
    private string[] stringSeparators_en;
    public List<bool> CheckLVL;
    public int Lvl = 0;
    public int lastScore;
    public int topScore;
    public string locale;

    void Awake()
    {
        dictionary = new List<List<string>>();
        dictionary_ru = new List<List<string>>(); 
        dictionaryNoun_ru = new List<List<string>>(); 
        dictionary_en = new List<List<string>>(); 
        if (Application.systemLanguage == SystemLanguage.Russian) {
            locale = "ru";
            //Debug.Log("This system is in Russian. ");
        }
        else if (Application.systemLanguage == SystemLanguage.English) {
            locale = "en";
            //Debug.Log("This system is in English. ");
        }
        LoadFileNounRu("ru");
        for (int i = 3; i < RU_MAX_FILE; i++) {
            LoadFileRu(i, "ru");           
        }
        for (int i = 3; i < EN_MAX_FILE; i++) {   
            LoadFileEn(i, "en");            
        }        
        osName();
    }

    private void Start()
    {
        CheckLVL = new List<bool>();
        topScore = PlayerPrefs.GetInt("Top Score", 0);
        var TBCSave = PlayerPrefs.GetString("Toggle Box", "00000").ToCharArray();        

        for (int i = 0; i < 5; i++)
        {
            if (TBCSave[i] == '1') {
                CheckLVL.Add(true);
            }
            else {
                CheckLVL.Add(false);
            }            
        }
        SceneManager.LoadScene("StartScene", LoadSceneMode.Single);        
    }

    public void ToggleBoxCheck()
    {
        ToggleBoxCheckSave = "";
        for (int i = 0; i < CheckLVL.Count; i++)
        {
            ToggleBoxCheckSave = ToggleBoxCheckSave + (CheckLVL[i] ? 1 : 0);
            PlayerPrefs.SetString("Toggle Box", ToggleBoxCheckSave);
        }
    }

    public void LoadWord()
    {
        lettersBuffer = "";
        lettersBufferString();
    }

    public void LoadFileNounRu(string locale)
    {
        TextAsset dictAsset = (TextAsset)Resources.Load("dictionary/ru/noun_list1k", typeof(TextAsset));
        List<string> words = new List<string>();

        if (isMacOsX || locale == "en") {
            stringSeparators = new string[] { "\n" };
        } else {
            stringSeparators = new string[] { "\r\n" };
        }
        
        var rawList = dictAsset.text.Split(stringSeparators, System.StringSplitOptions.None);

        foreach (var item in rawList)
        {
            words.Add(item);
        }
        dictionaryNoun_ru.Add(words);
    }

    public void LoadFileRu(int wordsLength, string locale)
    {
        string dict_Path;
        dict_Path = "dictionary/ru/" + wordsLength;
        
        TextAsset dictAsset = (TextAsset)Resources.Load(dict_Path, typeof(TextAsset));
        List<string> words = new List<string>();

        if (isMacOsX || locale == "en") {
            stringSeparators = new string[] { "\n" };
        } else {
            stringSeparators = new string[] { "\r\n" };
        }
        
        var rawList = dictAsset.text.Split(stringSeparators, System.StringSplitOptions.None);

        foreach (var item in rawList)
        {
            words.Add(item);            
        }
        dictionary_ru.Add(words);
    }

    public void LoadFileEn(int wordsLength, string locale)
    {
        string dict_Path;
        dict_Path = "dictionary/en/" + wordsLength;
        
        TextAsset dictAsset = (TextAsset)Resources.Load(dict_Path, typeof(TextAsset));
        List<string> words = new List<string>();

        if (isMacOsX || locale == "en") {
            stringSeparators = new string[] { "\n" };
        } else {
            stringSeparators = new string[] { "\r\n" };
        }
        
        var rawList = dictAsset.text.Split(stringSeparators, System.StringSplitOptions.None);

        foreach (var item in rawList)
        {
            words.Add(item);            
        }
        dictionary_en.Add(words);
    }

    private void lettersBufferString()
    {
        char[] arrayCon = null;
        char[] arrayVow = null;
        if (locale == "ru")
        {
            arrayCon = arrayConRu;
            arrayVow = arrayVowRu;
        }
        else if (locale == "en")
        {
            arrayCon = arrayConEn;
            arrayVow = arrayVowEn;
        }

        for (int i = 0; i < (arrayVow.Length + arrayCon.Length); i++)
        {
            if (i % 2 == 0)
            {
                int randomWordPosition = Random.Range(0, arrayCon.Length);
                lettersBuffer = lettersBuffer + "" + arrayCon[randomWordPosition];
            }
            else
            {
                int randomWordPosition = Random.Range(0, arrayVow.Length);
                lettersBuffer = lettersBuffer + "" + arrayVow[randomWordPosition];
            }
        }
    }

    public string GetNextLetter()
    {
        string randomLetter = lettersBuffer[0].ToString();
        lettersBuffer = lettersBuffer.Substring(1);
        if (lettersBuffer.Length <= lettersMinimumBuffer)
        {
            lettersBufferString();
        }
        
        return randomLetter;
    }

    public bool CheckWord(string userWord)
    {
        {
            if (locale == "ru") {
                dictionary = dictionary_ru;
            } else if (locale == "en") {
                dictionary = dictionary_en;
            }

            int wl = userWord.Length;
            if (wl > dictionary[dictionary.Count - 1][0].Length || wl < 3)
            {
                return false;
            }
            else
            {
                List<string> wordsForLength = dictionary[wl - 3];
                foreach (string word in wordsForLength)
                {
                    if (word == userWord)
                    {
                        return true;
                    }
                }
            }
            
        }
        return false;
    }

    private char[] arrayVowRu = new char[42] { 'о', 'о', 'о', 'о', 'о', 'о', 'о', 'о', 'о', 'о', //10
    'е', 'е', 'е', 'е', 'е', 'е', 'е', 'е', //8
    'а', 'а', 'а', 'а', 'а', 'а', 'а', 'а', //8 
    'и', 'и', 'и', 'и', 'и', 'и', 'и', //7 
    'у', 'у', 'у', //3  
    'я', 'я', //2
    'ы', 'ы', //2
    'ю', 'э'};
    private char[] arrayConRu = new char[62] { 'н', 'н', 'н', 'н', 'н', 'н', 'н',  //7
    'т', 'т', 'т', 'т', 'т', 'т',  //6
    'с', 'с', 'с', 'с', 'с',  //5
    'р', 'р', 'р', 'р', 'р',  //5
    'в', 'в', 'в', 'в', 'в',  //5
    'л', 'л', 'л', 'л',  //4
    'к', 'к', 'к',  //3
    'м', 'м', 'м',  //3
    'д', 'д', 'д',  //3
    'п', 'п', 'п',  //3
    'ь', 'ь',  //2
    'г', 'г',  //2
    'з', 'з',  //2
    'б', 'б',  //2
    'ч', 'ч',  //2
    'й', 'х', 'ж', 'ш', 'ц', 'щ', 'ф', 'ъ'};
    private char[] arrayVowEn = new char[41] { 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', //13
    'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a',  //8
    'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o',  //8 
    'i', 'i', 'i', 'i', 'i', 'i', 'i',  //7 
    'u', 'u', 'u',  //3  
    'y', 'y' //2
    };
    private char[] arrayConEn = new char[65] { 't', 't', 't', 't', 't', 't', 't', 't', 't',  //9
    'n', 'n', 'n', 'n', 'n', 'n', 'n',  //7
    'r', 'r', 'r', 'r', 'r', 'r',  //6
    's', 's', 's', 's', 's', 's',  //6
    'h', 'h', 'h', 'h', 'h', 'h',  //6
    'd', 'd', 'd', 'd',  //4
    'l', 'l', 'l', 'l',  //4
    'f', 'f', 'f',  //3
    'c', 'c', 'c',  //3
    'm', 'm', 'm',  //3
    'g', 'g',  //2
    'p', 'p',  //2
    'w', 'w',  //2
    'b', 'b',  //2
    'v', 'k', 'x', 'j', 'q', 'z'};    

    string windir = System.Environment.GetEnvironmentVariable("windir");
    public bool isWindows = false;
    public bool isLinux = false;
    public bool isMacOsX = false;
    public void osName()
    {
        if (!string.IsNullOrEmpty(windir) && windir.Contains(@"\") && Directory.Exists(windir))
        {
            isWindows = true;
        }
        else if (File.Exists(@"/proc/sys/kernel/ostype"))
        {
            string osType = File.ReadAllText(@"/proc/sys/kernel/ostype");
            if (osType.StartsWith("Linux", System.StringComparison.OrdinalIgnoreCase))
            {
                // Note: Android gets here too
                isLinux = true;
            }
        }
        else if (File.Exists(@"/System/Library/CoreServices/SystemVersion.plist"))
        {
            // Note: iOS gets here too
            isMacOsX = true;
        }
    }    
}

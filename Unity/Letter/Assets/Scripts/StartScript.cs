using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class StartScript : MonoBehaviour
{
    private DictManager dict;
    public GameObject StartButton;
    public Text GameLang;

    private void Awake()
    {
        dict = DictManager.GetManager();
        GLang();
    }
    
    void GLang()
    {        
        GameLang.text = "Lang: " + dict.locale;
        var SButton = "";
        if (dict.locale == "ru") {
            SButton = "Начать игру";
        } else if (dict.locale == "en") {
            SButton = "Start game";
        }
        StartButton.GetComponentInChildren<Text>().text = SButton;
    }

    public void GoToMenu()
    {      
        SceneManager.LoadScene("MenuScene", LoadSceneMode.Single);
    }

    public void EnLang()
    {
        dict.locale = "en";
        GLang();
    }

    public void RuLang()
    {
        dict.locale = "ru";
        GLang();
    }
}

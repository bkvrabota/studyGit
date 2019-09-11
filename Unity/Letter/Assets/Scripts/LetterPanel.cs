using System.Collections.Generic;
using UnityEngine;

public class LetterPanel : MonoBehaviour {
    
    public List<List<GameObject>> LPanel;

    void Start () {
        LPanel = new List<List<GameObject>>();
        LPanel.Add(new List<GameObject>());
        LPanel[0].Add(null);
    }
}

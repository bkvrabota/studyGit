using System.Collections.Generic;
using UnityEngine;

public class Letter : MonoBehaviour {
        
    public List<Sprite> letters_ru;
    public List<Sprite> letters_en;
	public int cellx;
	public int celly;
    public string letterInBlock;
    public bool moveIt = true;
    public bool clickIt = true;
    public bool locateToField = false;
    
    public void SetXY(int x, int y) {
		cellx = x;
		celly = y;
	}
    
	public void CreateLetter(string ltr) {
        if (ltr.Equals("а")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[0];
		} else if (ltr.Equals("б")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[1];
		} else if (ltr.Equals("в")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[2];
		} else if (ltr.Equals("г")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[3];
		} else if (ltr.Equals("д")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[4];
		} else if (ltr.Equals("е")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[5];
		} else if (ltr.Equals("ё")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[6];
		} else if (ltr.Equals("ж")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[7];
		} else if (ltr.Equals("з")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[8];
		} else if (ltr.Equals("и")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[9];
		} else if (ltr.Equals("й")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[10];
		} else if (ltr.Equals("к")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[11];
		} else if (ltr.Equals("л")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[12];
		} else if (ltr.Equals("м")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[13];
		} else if (ltr.Equals("н")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[14];
		} else if (ltr.Equals("о")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[15];
		} else if (ltr.Equals("п")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[16];
		} else if (ltr.Equals("р")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[17];
		} else if (ltr.Equals("с")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[18];
		} else if (ltr.Equals("т")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[19];
		} else if (ltr.Equals("у")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[20];
		} else if (ltr.Equals("ф")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[21];
		} else if (ltr.Equals("х")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[22];
		} else if (ltr.Equals("ц")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[23];
		} else if (ltr.Equals("ч")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[24];
		} else if (ltr.Equals("ш")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[25];
		} else if (ltr.Equals("щ")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[26];
		} else if (ltr.Equals("ъ")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[27];
		} else if (ltr.Equals("ы")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[28];
		} else if (ltr.Equals("ь")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[29];
		} else if (ltr.Equals("э")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[30];
		} else if (ltr.Equals("ю")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[31];
		} else if (ltr.Equals("я")) {
			GetComponent<SpriteRenderer>().sprite = letters_ru[32];
		} else if (ltr.Equals("a")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[0];
		} else if (ltr.Equals("b")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[1];
		} else if (ltr.Equals("c")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[2];
		} else if (ltr.Equals("d")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[3];
		} else if (ltr.Equals("e")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[4];
		} else if (ltr.Equals("f")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[5];
		} else if (ltr.Equals("g")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[6];
		} else if (ltr.Equals("h")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[7];
		} else if (ltr.Equals("i")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[8];
		} else if (ltr.Equals("j")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[9];
		} else if (ltr.Equals("k")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[10];
		} else if (ltr.Equals("l")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[11];
		} else if (ltr.Equals("m")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[12];
		} else if (ltr.Equals("n")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[13];
		} else if (ltr.Equals("o")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[14];
		} else if (ltr.Equals("p")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[15];
		} else if (ltr.Equals("q")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[16];
		} else if (ltr.Equals("r")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[17];
		} else if (ltr.Equals("s")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[18];
		} else if (ltr.Equals("t")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[19];
		} else if (ltr.Equals("u")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[20];
		} else if (ltr.Equals("v")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[21];
		} else if (ltr.Equals("w")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[22];
		} else if (ltr.Equals("x")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[23];
		} else if (ltr.Equals("y")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[24];
		} else if (ltr.Equals("z")) {
			GetComponent<SpriteRenderer>().sprite = letters_en[25];
        }
        letterInBlock = ltr;
    }	
}

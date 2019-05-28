using UnityEngine.EventSystems;
using UnityEngine;

public class FingerMove : MonoBehaviour, IPointerDownHandler, IDragHandler, IPointerUpHandler
{
    public GameController gameController;
    public string userWord;
    public Vector3 blockPos;
    private bool blockDragging = false;
    private GameObject block = null;    
    private bool swipeOn = false;

    public void OnPointerDown(PointerEventData data)
    {
        if (data.pointerEnter != null)
        {
            if (data.pointerEnter.tag == "GameController")
            {
                blockDragging = false;
            }
            else if (data.pointerEnter.tag == "Tile")
            {
                blockDragging = true;
                block = data.pointerEnter.transform.gameObject;
                gameController.previousCellX = block.GetComponent<Letter>().cellx;
                gameController.previousCellY = block.GetComponent<Letter>().celly;
                gameController.CheckedWord(block);
                if (!block.GetComponent<Letter>().clickIt) {
                    swipeOn = true;
                } else {
                    swipeOn = false;
                }
            }
        }
    }
       
    public void OnDrag(PointerEventData data)
    {
        if (Input.touchCount == 1 || Input.mousePresent)
        {
            if (swipeOn && data.pointerEnter.tag == "Tile") {
                block = data.pointerEnter.transform.gameObject;
                gameController.previousCellX = block.GetComponent<Letter>().cellx;
                gameController.previousCellY = block.GetComponent<Letter>().celly;
                gameController.CheckedWord(block);
            } else if (data.pointerEnter != null && data.pointerEnter.tag == "GameController" && !blockDragging)
            {
                float camHalfH = Camera.main.orthographicSize * 2f;
                float camHalfW = Camera.main.aspect * camHalfH;
                float bgWidth = data.pointerEnter.GetComponent<SpriteRenderer>().size.x;
                float bgHeight = data.pointerEnter.GetComponent<SpriteRenderer>().size.y;
                var newPos = Camera.main.transform.position - new Vector3(data.delta.x, data.delta.y, 0);
                if (Mathf.Abs(newPos.x - camHalfW / 2) <= bgWidth / 2 &&
                    Mathf.Abs(newPos.x + camHalfW / 2) <= bgWidth / 2 &&
                    Mathf.Abs(newPos.y - camHalfH / 2) <= bgHeight / 2 &&
                    Mathf.Abs(newPos.y + camHalfH / 2) <= bgHeight / 2
                )
                {
                    Camera.main.transform.position = newPos;
                    var LPanel = GameObject.FindGameObjectWithTag("LPanel");
                    LPanel.transform.localPosition = LPanel.transform.localPosition +
                                (new Vector3(-data.delta.x, -data.delta.y, 0) * Camera.main.orthographicSize * 0.0011131934f);
                    var CellPanel = GameObject.FindGameObjectWithTag("Cell");
                    CellPanel.transform.localPosition = CellPanel.transform.localPosition +
                                (new Vector3(data.delta.x, data.delta.y, 0) * Camera.main.orthographicSize * 1.1f / Screen.height);
                }
                else if (Mathf.Abs(newPos.x - camHalfW / 2) <= bgWidth / 2 &&
                         Mathf.Abs(newPos.x + camHalfW / 2) <= bgWidth / 2)
                {
                    Camera.main.transform.position = Camera.main.transform.position - new Vector3(data.delta.x, 0, 0);
                    var LPanel = GameObject.FindGameObjectWithTag("LPanel");
                    LPanel.transform.localPosition = LPanel.transform.localPosition +
                                (new Vector3(-data.delta.x, 0, 0) * Camera.main.orthographicSize * 0.0011131934f);
                    var CellPanel = GameObject.FindGameObjectWithTag("Cell");
                    CellPanel.transform.localPosition = CellPanel.transform.localPosition +
                                (new Vector3(data.delta.x, 0, 0) * Camera.main.orthographicSize * 1.1f / Screen.height);
                }
            }
            else if (blockDragging && block.GetComponent<Letter>().moveIt == true)
            {
                block.transform.localPosition = block.transform.localPosition +
                    (new Vector3(data.delta.x, data.delta.y, 0) * Camera.main.orthographicSize * 2f / Screen.height);
            }
        }
    }

    public void OnPointerUp(PointerEventData data)
    {
        swipeOn = false;
        if (data.pointerEnter.tag == "Tile") {
            gameController.Check_OnClick();
        }
        if (blockDragging && block.GetComponent<Letter>().moveIt == true) {
            gameController.TryToLocateBlock(block);
        }
        blockDragging = false;
        block = null;
    }
}

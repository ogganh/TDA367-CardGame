import java.util.ArrayList;

public class CardPile extends AbstractCardPile implements PlayableStackInterface {

    /*
    Representerar en hög med kort där kort kan läggas till och tas bort. Tänk en hög på ett borde eller en hand. 
    Inte bunden av något högre värde till skillandf från CardDeck.
     */

    public CardPile() {
        cards = new ArrayList<Card>();
    }    
}

package generator;

import java.util.Collections;
import java.util.Stack;

/**
 * Created by pavel on 02.11.17.
 */
public class MemeGenerator {

    private Stack<String> stack = new Stack<>();

    private final String[] stringMemes = {
            "- Черт побери ! Эта даже с музыкой ..."
            , "Если кто-то где-то, кое-где у нас порой, честно жить нееееее"
            , "Избыток вкуса отбивает вкус."
            , "В кустах игрушечные волки\n Глазами страшными глядят."
            , "Ведь храбрецы средь них найдутся. \nДрузьям раздайте по ружью, \nИ дураки переведутся. \nКогда последний враг упал, \nТруба победу проиграла - \nЛишь в этот миг я осознал. \nНасколько нас осталось мало!"
            , "Просмотр «Ганнибал» максимально примиряет с действительностью"
            , "API, SDK, Автоматизация - наши стратегические цели."
            , "Говорят, что половина планктона в осенней депрессии. Найдите себе работу что ли. Чтобы работать надо ..."
            , "Ненуачо, половина мужчин ИМХО всю жизнь беременны …"
            , "А никто еще не предположил что Ксения это преемниЦ ?"
            , "images/1.png"
            , "images/2.png"
            , "images/3.png"
    };

    public String getMeme() {
        if (stack.empty()) {
            for (String meme : stringMemes) {
                stack.push(meme);
            }
            Collections.shuffle(stack);
        }
        return stack.pop();
    }


}

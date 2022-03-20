import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;

public class GenerateTestCaseAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        //BrowserUtil.browse("https://stackoverflow.com/questions/ask");
        //code here

        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        CaretModel caretModel = editor.getCaretModel();
        String selectedText = caretModel.getCurrentCaret().getSelectedText();

        System.out.println(selectedText);
    }
}

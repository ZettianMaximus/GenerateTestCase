//removed importing BrowserUtil
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;

import com.intellij.pom.Navigatable; //can be used to obtain selected method
import com.intellij.openapi.project.Project; //can be used to get selected project

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class GenerateTestCaseAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {

        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        CaretModel caretModel = editor.getCaretModel();
        String selectedText = caretModel.getCurrentCaret().getSelectedText();

        System.out.println(selectedText);

        Project currentProject = e.getProject();
        //Navigatable nav = e.getData(CommonDataKeys.PSI_ELEMENT); //I want to select the method? M
        //Navigatable nav = e.getData(CommonDataKeys.NAVIGATABLE); //This returns PsiMethod:methodname

        try
        {
            File testFile = new File("C:\\TestCase.java"); //and place method name here M

            if (testFile.createNewFile())
            {
                System.out.println("File created: " + testFile.getName());
            }
            else
            {
                System.out.println("File already exists!");
            }

            FileWriter testFileWriter = new FileWriter("C:\\TestCase.java");  //and here M
            testFileWriter.write("" +
                    "import static org.junit.jupiter.api.Assertions.*;\n" +
                    "\n" +
                    "class FibonacciUnitTest {\n" +
                    "\n" +
                    "    @org.junit.jupiter.api.Test\n" +
                    "    void nthFibonacciTermTest() {\n" +
                    "        FibonacciUnit f = new FibonacciUnit();\n" +
                    "        assertEquals( 3, f.nthFibonacciTerm(4));\n" +
                    "    }\n" +
                    "}");  // the hope is to get the method name and use it in this test writer.
            testFileWriter.close();
        }
        catch (IOException exception)
        {
            System.out.println("An error occurred!");
            exception.printStackTrace();
        }
    }
}

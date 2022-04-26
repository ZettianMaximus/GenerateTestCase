import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
//import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;

import com.intellij.psi.PsiMethod;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateTestCaseAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        //Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);
        if (editor == null || psiFile == null) { // Checking nulls for carrot and file
            return;
        }
        int offset = editor.getCaretModel().getOffset();

        PsiElement element = psiFile.findElementAt(offset);
        String className = "none";
        String methodName = "none";

        //CaretModel caretModel = editor.getCaretModel();
        //String selectedText = caretModel.getCurrentCaret().getSelectedText();

        //System.out.println(selectedText);

        if (element != null) {
            PsiMethod containingMethod = PsiTreeUtil.getParentOfType(element, PsiMethod.class);
            methodName = containingMethod != null ? containingMethod.getName() : "none";

            if (containingMethod != null) {
                PsiClass containingClass = containingMethod.getContainingClass();
                className = containingClass != null ? containingClass.getName() : "none";

                try {
                    File testFile = new File("C:\\" + className + "Test.java"); //and place class name here C

                    if (testFile.createNewFile()) {
                        System.out.println("File created: " + testFile.getName());
                    } else {
                        System.out.println("File already exists!");
                    }

                    FileWriter testFileWriter = new FileWriter("C:\\" + className + "Test.java");  //and here C
                    testFileWriter.write("" +
                            "import static org.junit.jupiter.api.Assertions.*;\n" +
                            "\n" +
                            "class " + className + "Test {\n" +
                            "\n" +
                            "    @org.junit.jupiter.api.Test\n" +
                            "    void " + methodName + "Test() {\n" +
                            "        " + className + " f = new " + className + "();\n" +
                            "        assertEquals( 3 , f." + methodName + "(  4  ));  //input your values\n" +
                            "    }\n" +
                            "}");  // the hope is to get the method name and use it in this test writer. Replacing the Class and method.
                    testFileWriter.close();
                } catch (IOException exception) {
                    System.out.println("An error occurred!");
                    exception.printStackTrace();
                }
            }
        }
    }
}

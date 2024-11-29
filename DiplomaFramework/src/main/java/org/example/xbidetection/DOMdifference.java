package org.example.xbidetection;

import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

public class DOMdifference {
    private VisualDifference visDiff;
    private WebElement testElement;
    private WebElement baselineElement;
    private String category;

    public DOMdifference(VisualDifference visDiff, WebElement webElement, String category) {
        this.visDiff = visDiff;
        this.testElement = webElement;
        this.category = category;
    }

    private void findDriverAndXpathes() {
        WebDriver baselineDriver = visDiff.getBaselineScreenshot().getDriver();
        //System.out.println("(method 'findDriverAndXpathes') test elem == null:");
        //System.out.println(testElement==null);
        if (testElement!=null) {
            String xpath = getXPath(testElement, visDiff.getTestScreenshot().getDriver());
            try{
                this.baselineElement = baselineDriver.findElement(By.xpath(xpath));
            }catch (NoSuchElementException e){
                System.out.println("Element not found");
            }
        }else{
            baselineElement = null;
        }
    }

    private Incompatibility createIncompatibility(String type, String details) {
        Incompatibility i = new Incompatibility(type, details);
        i.setDetectedDifference(this);
        return i;
    }

    private List<Incompatibility> compareVisibility() {
        List<Incompatibility> issues = new ArrayList<>();
        String type = "Різна видимість";
        if (!baselineElement.isDisplayed() && testElement.isDisplayed()) {
            issues.add(createIncompatibility(type, "Елемент видимий в тестуючому браузері але не видимий у базовому."));
        } else if (baselineElement.isDisplayed() && !testElement.isDisplayed()) {
            issues.add(createIncompatibility(type, "Елемент видимий в базовому браузері але не видимий в тестуючому."));
        }
        return issues;
    }


    private List<Incompatibility> comparePositionAndSize() {
        List<Incompatibility> issues = new ArrayList<>();
        Rectangle baselineRect = baselineElement.getRect();
        Rectangle testRect = testElement.getRect();
        // Position comparison
        int deltaX = Math.abs(baselineRect.x - testRect.x);
        int deltaY = Math.abs(baselineRect.y - testRect.y);
        if (deltaX > 40 || deltaY > 40) {
            issues.add(createIncompatibility("Різне позиціювання", "Базовий: (" + baselineRect.x + "," + baselineRect.y + "), Тестуючий: (" + testRect.x + "," + testRect.y + ")"));
        }
        // Size comparison
        int deltaWidth = Math.abs(baselineRect.width - testRect.width);
        int deltaHeight = Math.abs(baselineRect.height - testRect.height);
        if (deltaWidth > 15 || deltaHeight > 15) {
            issues.add(createIncompatibility("Різний розмір", "Базовий: " + baselineRect.width + "x" + baselineRect.height + ", Тестуючий: " + testRect.width + "x" + testRect.height));
        }
        return issues;
    }

    private List<Incompatibility> compareAppearance() {
        List<Incompatibility> issues = new ArrayList<>();
        // Compare color
        String baselineColor = baselineElement.getCssValue("color");
        String testColor = testElement.getCssValue("color");
        if (!baselineColor.equals(testColor)) {
            issues.add(createIncompatibility("Різний зовнішній вигляд", "Невідповідність кольору. Базовий: " + baselineColor + ", Тестуючий: " + testColor));
        }
        // Compare font properties
        String baselineFont = baselineElement.getCssValue("font");
        String testFont = testElement.getCssValue("font");
        if (!baselineFont.equals(testFont)) {
            issues.add(createIncompatibility("Різний зовнішній вигляд", "Невідповідність шрифту. Базовий: " + baselineFont + ", Тестуючий: " + testFont));
        }
        // Compare content
        String baselineText = baselineElement.getText();
        String testText = testElement.getText();
        if (!baselineText.equals(testText)) {
            issues.add(createIncompatibility("Різний контент",  "Невідповідність контенту. Базовий: \"" + baselineText + "\", Тестуючий: \"" + testText + "\""));
        }
        return issues;
    }

    private String getXPath(WebElement element, WebDriver driver) {
//        System.out.println("Id of element "+ element.getAttribute("id"));
        //if(element!=null && element.getAttribute("id")!= )

        JavascriptExecutor js = (JavascriptExecutor) driver;

//        String xpath = (String) js.executeScript(
//                "function getXPath(element) {" +
//                        "    if (element.id !== '' || element.id !== undefined || element.id !== null) {" +
//                        "        return 'id(\"' + element.id + '\")';" +
//                        "    }" +
//                        "    if (element === document.body) {" +
//                        "        return element.tagName;" +
//                        "    }" +
//                        "    var ix = 0;" +
//                        "    var siblings = element.parentNode.childNodes;" +
//                        "    for (var i = 0; i < siblings.length; i++) {" +
//                        "        var sibling = siblings[i];" +
//                        "        if (sibling === element) {" +
//                        "            return getXPath(element.parentNode) + '/' + element.tagName + '[' + (ix + 1) + ']';" +
//                        "        }" +
//                        "        if (sibling.nodeType === 1 && sibling.tagName === element.tagName) {" +
//                        "            ix++;" +
//                        "        }" +
//                        "    }" +
//                        "} return getXPath(arguments[0]);", element);
//        System.out.println("xpath: "+xpath);
//        return xpath;
//        JavascriptExecutor jsExecutor = (JavascriptExecutor) ((WrapsDriver) element).getWrappedDriver();
        String xpath = (String) js.executeScript(
                "function absoluteXPath(element) {" +
                        "var comp, comps = [];" +
                        "var parent = null;" +
                        "var xpath = '';" +
                        "var getPos = function(element) {" +
                        "var position = 1, curNode;" +
                        "if (element.nodeType == Node.ATTRIBUTE_NODE) {" +
                        "return null;" +
                        "}" +
                        "for (curNode = element.previousSibling; curNode; curNode = curNode.previousSibling) {" +
                        "if (curNode.nodeName == element.nodeName) {" +
                        "++position;" +
                        "}" +
                        "}" +
                        "return position;" +
                        "};" +
                        "if (element instanceof Document) {" +
                        "return '/';" +
                        "}" +
                        "for (; element && !(element instanceof Document); element = element.nodeType == Node.ATTRIBUTE_NODE ? element.ownerElement : element.parentNode) {" +
                        "comp = comps[comps.length] = {};" +
                        "switch (element.nodeType) {" +
                        "case Node.TEXT_NODE:" +
                        "comp.name = 'text()';" +
                        "break;" +
                        "case Node.ATTRIBUTE_NODE:" +
                        "comp.name = '@' + element.nodeName;" +
                        "break;" +
                        "case Node.PROCESSING_INSTRUCTION_NODE:" +
                        "comp.name = 'processing-instruction()';" +
                        "break;" +
                        "case Node.COMMENT_NODE:" +
                        "comp.name = 'comment()';" +
                        "break;" +
                        "case Node.ELEMENT_NODE:" +
                        "comp.name = element.nodeName;" +
                        "break;" +
                        "}" +
                        "comp.position = getPos(element);" +
                        "}" +
                        "for (var i = comps.length - 1; i >= 0; i--) {" +
                        "comp = comps[i];" +
                        "xpath += '/' + comp.name.toLowerCase();" +
                        "if (comp.position !== null) {" +
                        "xpath += '[' + comp.position + ']';" +
                        "}" +
                        "}" +
                        "return xpath;" +
                        "} return absoluteXPath(arguments[0]);", element);
//        System.out.println("xpath: "+xpath);
        return xpath;
    }


    public List<Incompatibility> detectXBI() {
        findDriverAndXpathes();
        List<Incompatibility> issues = new ArrayList<>();
//        System.out.println("(method 'detectXBI') test elem == null:");
//        System.out.println(testElement==null);
//        System.out.println(this.testElement==null);
        if(this.testElement == null){
            Incompatibility noElem = new Incompatibility("Веб елемент не було ідентифіковано");
            noElem.setDetectedDifference(this);
            issues.add(noElem);
        }else if (this.baselineElement == null && this.testElement != null){
            Incompatibility notFindElem = new Incompatibility("Відсутність елемента");
            notFindElem.setDetectedDifference(this);
            issues.add(notFindElem);
        } else {
            List<Incompatibility> visibilityIssues = compareVisibility();
            List<Incompatibility> positionAndSizeIssues = comparePositionAndSize();
            List<Incompatibility> appearanceIssues = compareAppearance();
            if(!visibilityIssues.isEmpty()){issues.addAll(visibilityIssues);}
            if(!positionAndSizeIssues.isEmpty()){issues.addAll(positionAndSizeIssues);}
            if(!appearanceIssues.isEmpty()){issues.addAll(appearanceIssues);}
        }
//        System.out.println("issues: "+issues.size());
        return issues;
    }

    public WebElement getTestWebElement() {
        return testElement;
    }

    public WebElement getBaselineWebElement() {
        return baselineElement;
    }

    public VisualDifference getVisDiff() {
        return visDiff;
    }

    public String getCategory(){
        return category;
    }
}

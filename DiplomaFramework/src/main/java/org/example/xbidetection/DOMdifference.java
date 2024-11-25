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
        String xpath = getXPath(testElement);
        this.baselineElement = baselineDriver.findElement(By.xpath(xpath));
    }

    private Incompatibility createIncompatibility(String type, String details) {
        Incompatibility i = new Incompatibility(type, details);
        i.setDetectedDifference(this);
        return i;
    }

    private List<Incompatibility> compareVisibility() {
        List<Incompatibility> issues = new ArrayList<>();
        String type = "Visibility Difference";
        if (!baselineElement.isDisplayed() && testElement.isDisplayed()) {
            issues.add(createIncompatibility(type, "Element visible in test browser but not in baseline browser."));
        } else if (baselineElement.isDisplayed() && !testElement.isDisplayed()) {
            issues.add(createIncompatibility(type, "Element visible in baseline browser but not in test browser."));
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
            issues.add(createIncompatibility("Position Difference", "Baseline: (" + baselineRect.x + "," + baselineRect.y + "), Test: (" + testRect.x + "," + testRect.y + ")"));
        }
        // Size comparison
        int deltaWidth = Math.abs(baselineRect.width - testRect.width);
        int deltaHeight = Math.abs(baselineRect.height - testRect.height);
        if (deltaWidth > 15 || deltaHeight > 15) {
            issues.add(createIncompatibility("Size Difference", "Baseline: " + baselineRect.width + "x" + baselineRect.height + ", Test: " + testRect.width + "x" + testRect.height));
        }
        return issues;
    }

    private List<Incompatibility> compareAppearance() {
        List<Incompatibility> issues = new ArrayList<>();
        // Compare color
        String baselineColor = baselineElement.getCssValue("color");
        String testColor = testElement.getCssValue("color");
        if (!baselineColor.equals(testColor)) {
            issues.add(createIncompatibility("Appearance Difference", "Color mismatch. Baseline: " + baselineColor + ", Test: " + testColor));
        }
        // Compare font properties
        String baselineFont = baselineElement.getCssValue("font");
        String testFont = testElement.getCssValue("font");
        if (!baselineFont.equals(testFont)) {
            issues.add(createIncompatibility("Appearance Difference", "Font mismatch. Baseline: " + baselineFont + ", Test: " + testFont));
        }
        // Compare content
        String baselineText = baselineElement.getText();
        String testText = testElement.getText();
        if (!baselineText.equals(testText)) {
            issues.add(createIncompatibility("Content Difference",  "Content mismatch. Baseline: \"" + baselineText + "\", Test: \"" + testText + "\""));
        }
        return issues;
    }

    private String getXPath(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) ((WrapsDriver) element).getWrappedDriver();
        return (String) jsExecutor.executeScript(
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
    }


    public List<Incompatibility> detectXBI() {
        findDriverAndXpathes();
        List<Incompatibility> issues = new ArrayList<>();
        if (this.testElement != null) {
            Incompatibility noElem = new Incompatibility("Not existing element");
            noElem.setDetectedDifference(this);
            issues.add(noElem);
        } else {
            List<Incompatibility> visibilityIssues = compareVisibility();
            List<Incompatibility> positionAndSizeIssues = comparePositionAndSize();
            List<Incompatibility> appearanceIssues = compareAppearance();
            if(!visibilityIssues.isEmpty()){issues.addAll(visibilityIssues);}
            if(!positionAndSizeIssues.isEmpty()){issues.addAll(positionAndSizeIssues);}
            if(!appearanceIssues.isEmpty()){issues.addAll(appearanceIssues);}
        }
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

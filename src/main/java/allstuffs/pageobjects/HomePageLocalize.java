package allstuffs.pageobjects;

import allstuffs.helpers.MethodHelper;

import java.util.HashMap;
import java.util.Map;

public class HomePageLocalize extends AbstractPage{
    public  static HashMap<String, Boolean> sumResult = new HashMap<String, Boolean>();

    public HomePageLocalize() {
        languageElements = getLanguageElements("HomePageLocalize");
    }

    public void launchWebAppLanguage(String language){
        if(language.equals("English")){
            openUrl("https://www.un.org/en");
        }else if(language.equals("France")){
            openUrl("https://www.un.org/fr");
        }
    }

    public  HashMap<String, String> getListLocatorAndText(String Language,String... elementsName) {
        MethodHelper methodHelper = new MethodHelper();
        HashMap<String, String> listLocatorAndText = new HashMap<String, String>();
        String[] listLocator,listText;
        listLocator = methodHelper.readLanguageCSV("language", "Locator");
        listText = methodHelper.readLanguageCSV("language", Language);
        for (int i= 0; i<elementsName.length;i++) {
            for(int j=1;j<listLocator.length;j++)
            {
                if(elementsName[i].equals(listLocator[j])){
                    System.out.println("list element to check " + elementsName[i]);
                    listLocatorAndText.put(listLocator[j],listText[j]);
                }
            }
        }
        return listLocatorAndText;
    }

    public Map<String, Boolean> getListLocatorAndResult(Map<String, String> ListLocatorAndText){
        Map<String, Boolean> resultMethodPage = new HashMap<String, Boolean>();
        for (Map.Entry<String, String> set :ListLocatorAndText.entrySet()) {
            resultMethodPage =checkLanguageForEachElement(set.getKey(),set.getValue());
        }
        return resultMethodPage;
    }
    public HashMap<String, Boolean>  checkLanguageForEachElement(String xpath, String text){
        waitToElementVisible(xpath);
        String replaceEnterAfterGetText= getTextElement(xpath).replace("\n"," ");
        sumResult.put(xpath, replaceEnterAfterGetText.equals(text));
        return  sumResult;

    }

}

package allstuffs.models;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class EnvironmentData {
    public HashMap<String, String> environmentUrls;
    public HashMap<String, String> languageUrls;
    public HashMap<String, HashMap<String, String>> existingUsers;
    public HashMap<String, HashMap<String, String>> existingElementLanguages;


    public EnvironmentData(String environment) {
        Map env = null;
        Map<?,?> elementLanguage = null;
        File file = new File("./src/test/resources/testdata/" + environment + "_environment.yml");
        File fileLanguage = new File("./src/test/resources/testdata/language.yml");
        Yaml yaml = new Yaml();
        try {
            env = (HashMap<String, HashMap<String, Object>>) yaml.load(new FileInputStream(file));
            elementLanguage = (HashMap<String, HashMap<String, Object>>) yaml.load(new FileInputStream(fileLanguage));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        environmentUrls = (HashMap<String, String>) env.get("environment_urls");
        languageUrls = (HashMap<String, String>) elementLanguage.get("france");
        existingUsers = (HashMap<String, HashMap<String, String>>) env.get("existing_users");
        existingElementLanguages =  (HashMap<String, HashMap<String, String>>) elementLanguage.get("france");

    }
}

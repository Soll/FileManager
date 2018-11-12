package aop.objects;

import aop.annotations.ShowResult;
import aop.annotations.ShowTime;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

//класс содержит 2 метода для определния списка расширений и подсчет количества расширений в папке
@Component
public class FileManager {

    //возвращает упорядоченный сет из расширентй в папке
    @ShowResult
    public Set<String> getExtensionsList(String folder) {

        File dir = new File(folder);
        Set<String> extList = new TreeSet<String>();

        for (String fileName : dir.list()) {

            File file = new File(dir.getAbsolutePath() + "\\" + fileName);
            int i = fileName.lastIndexOf(".");

            if (file.isFile() && i != -1) {
                extList.add(fileName.substring(i + 1, fileName.length()).toLowerCase());
            }
        }
        return extList;
    }

    //возвращает карту с парами - Расширение - Количество расширений
   @ShowTime
    public Map<String, Integer> getExtensionsCount(String folder) {

        File dir = new File(folder);
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (String extension : getExtensionsList(folder)) {
            FilenameFilter filter = new CustomFileFilter(extension);
            map.put(extension, dir.listFiles(filter).length);
        }
        return map;
    }
}

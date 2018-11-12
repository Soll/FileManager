package aop.objects;

import java.io.File;
import java.io.FilenameFilter;

public class CustomFileFilter implements FilenameFilter {

    String ext;

    public CustomFileFilter(String ext) {
        this.ext = ext;
    }

    public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith("." + ext.toLowerCase());
    }
}


package bankapp;
import java.io.File;
import javax.swing.filechooser.FileFilter;


class BofFilter extends FileFilter {

    public BofFilter() {
    }

    @Override
    public boolean accept(File f) {
        if(f.isDirectory() || f.getName().toLowerCase().endsWith(".bof"))
            return true;
        
        else
            return false;
    }

    @Override
    public String getDescription() {
        return "Bank Object File";
    }
    
}

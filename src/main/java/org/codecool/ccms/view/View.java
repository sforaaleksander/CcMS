package org.codecool.ccms.view;
import com.jakewharton.fliptables.FlipTable;
import org.codecool.ccms.models.Displayable;
import java.util.ArrayList;
import java.util.List;

public class View {
    private String[] commandHeaders;
    private String[] querryHeaders;
    private List<Displayable> querryList;
    private List<Displayable> commandList;

    public View(){
        this.commandHeaders = new String[]{"Key:", "Action:"};
        this.querryHeaders = new String[]{"Id", "Name", "Surname", "Role", "Category"};
        this.querryList = new ArrayList<>();
        this.commandList = new ArrayList<>();
    }

    public void setQuerryList(List<Displayable> querryList) {
        this.querryList = querryList;
    }
    public void setQuerryHeaders(String[] querryHeaders) {
        this.querryHeaders = querryHeaders;
    }
    public void setCommandList(List<Displayable> commandList) { this.commandList = commandList; }
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
    }
    public void displayMessage(String message) {
        System.out.println(message);
    }
    public void displayContent() {
        clearScreen();
        String[] superHeader = {"Actions", "Output"};
        String[][] contentContainers = {{displayContainer(commandHeaders, commandList) ,displayContainer(querryHeaders, querryList)}};
        System.out.println(FlipTable.of(superHeader, contentContainers));
    }

    public List<Displayable> getQuerryList() {
        return querryList;
    }

    public int getQuerryListLenght() {
        return querryList.size();
    }

    public String displayContainer(String[] headers, List<Displayable> rowList){
        if (rowList.isEmpty()) return "";
        String[][] screen = new String[rowList.size()][headers.length];
        for (int i=0; i<rowList.size(); i++){
            String[] line = rowList.get(i).toStringList();
            for (int j=0; j<headers.length; j++) {
                if (line[j]!=null) screen[i][j] = line[j];
                else screen[i][j] = "null";
            }
        }
        String outputString = FlipTable.of(headers, screen);
        return outputString;
    }
}

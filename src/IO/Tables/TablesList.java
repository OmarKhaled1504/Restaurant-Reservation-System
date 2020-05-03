package IO.Tables;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tables")
@XmlAccessorType(XmlAccessType.FIELD)

public class TablesList {

    @XmlElement(name = "table")
    private List<Table> tables;

    public List<Table> getTablesList() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}

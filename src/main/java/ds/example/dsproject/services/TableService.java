package ds.example.dsproject.services;

import ds.example.dsproject.dto.TableITDTO;;

import java.util.List;

public interface TableService {
    List<TableITDTO> getTables();
    TableITDTO addTable(TableITDTO tableITDTO);
    TableITDTO MostReserved ();
    List<TableITDTO> deleteTable(Long id);
    TableITDTO updateTable(TableITDTO tableITDTO);
}

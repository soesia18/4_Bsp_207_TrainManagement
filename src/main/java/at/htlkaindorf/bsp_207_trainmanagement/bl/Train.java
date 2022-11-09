package at.htlkaindorf.bsp_207_trainmanagement.bl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Train {
    private int id;
    private List<String> stations = new ArrayList<>();
    private String type;

    public void addStation (String station) {
        stations.add(station);
    }
}

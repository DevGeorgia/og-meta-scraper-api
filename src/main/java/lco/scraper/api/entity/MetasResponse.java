package lco.scraper.api.entity;

import java.util.ArrayList;
import java.util.List;

public class MetasResponse {

    private List<MetaResponse> metaResponseList = new ArrayList<>();

    public List<MetaResponse> getMetaList() {
        return metaResponseList;
    }

    public void setMetaList(List<MetaResponse> metaResponseList) {
        this.metaResponseList = metaResponseList;
    }
}

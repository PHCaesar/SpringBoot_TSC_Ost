package at.tsc.sj23_tsc_ost.domain;

public enum Role {
    CAPTAIN(2l), MEMBER(1l);

    Role(Long id){
        this.id=id;
    }

    private Long id;
}

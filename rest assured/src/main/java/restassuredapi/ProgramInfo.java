package restassuredapi;

public class ProgramInfo
{
    private int programId;
    private String programName;
    private String programDescription;
    private String online;

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public ProgramInfo(int programId, String programName, String programDescription, String online) {
        this.programId = programId;
        this.programName = programName;
        this.programDescription = programDescription;
        this.online = online;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }
}

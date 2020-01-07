package gov.nih.nci.cadsr.common;

public class Constants {
	//public static final String DEFAULT_CONTEXT = "NCIP";	//GF32649
	public static final String DEFAULT_MANDATORY_ATTRIBUTE_TEXT = "This field is Mandatory. \n";
	public static final String DEC_CDR_NAME = "dec_cdr_name";
	//GF30798
	//create
	public static final String USER_SELECTED_ALTERNATE_DEF_COMP1 = "objectQualifierMap_UserALtDef";
	public static final String USER_SELECTED_ALTERNATE_DEF_COMP2 = "object_UserALtDef";
	public static final String USER_SELECTED_ALTERNATE_DEF_COMP3 = "propQualifierMap_UserALtDef";
	public static final String USER_SELECTED_ALTERNATE_DEF_COMP4 = "prop_UserALtDef";
	public static final String FINAL_ALT_DEF_STRING = "userSelectedDefFinal";
	//edit
	public static final String USER_SELECTED_ALTERNATE_DEF_COMP1_COUNT = "userSelectedDef_Comp1_Count";
	public static final String USER_SELECTED_ALTERNATE_DEF_COMP2_COUNT = "userSelectedDef_Comp2_Count";
	public static final String USER_SELECTED_ALTERNATE_DEF_COMP3_COUNT = "userSelectedDef_Comp3_Count";
	public static final String USER_SELECTED_ALTERNATE_DEF_COMP4_COUNT = "userSelectedDef_Comp4_Count";
    //GF7680 
    public static final String WORKFLOW_STATUS_RELEASED = "RELEASED";
    public static final String WORKFLOW_STATUS_NOT_RELEASED = "NOT RELEASED";
    public static final String VD_USED_IN_FORM = "VDUsedInForm";
    public static final String PV_NAME = "txtpvonly";
	//GF32723
	public static final String USER_SELECTED_VOCAB = "userSelectedDtsVocab";
	public static final String USER_SELECTED_CON_CODE = "userSelectedConceptCode";
	public static final String DTS_VOCAB_NCIT = "NCI Thesaurus";
	public static final String DTS_VOCAB_NCI_META = "NCI MetaThesaurus";
	public static final String DEC_EVS_LOOKUP_FLAG = "EVS_LOOKUP_FLAG";
	
	//GF32153
	public static final String ERR_LOGON_ISSUE = "Could not validate the User Name and Password, please try again.";
	//JR1016
	public static final String COMP_TYPE = "SelectedComponentType";
	public static final String OC_ALT_DEF = "GeneratedAltDefOC";
	public static final String PROP_ALT_DEF = "GeneratedAltDefProp";
	//JR676/GF33178
	public static final String NEW_VERSION_HID_ACTION = "hidaction";
	//JR1000
	public static final String USER_SELECTED_DOWNLOAD_REQUEST = "userSelectedDownloadRequest";
	public static final String USER_SELECTED_DOWNLOAD_TYPE = "userSelectedDownload";

	//JR1053
	public static final String NCI_REGISTRY_VALUE = "2.16.840.1.113883.3.26.2";
	//JR1024/JR1025 restore 4 
	public static final String NEW_PV = "pvNew";
	public static final int NEW_PV_INDEX = -1;
	public static final String USER_SELECTED_VM = "userSelectedVM";
	//JR1105
	public static final String USER_EDITED_PV_VALUE = "currentPVValue";
}
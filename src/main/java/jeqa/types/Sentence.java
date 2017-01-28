

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;
import jeqa.types.ja.KNP;
import jeqa.types.ja.ChaPAS;


/** Sentence
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class Sentence extends TextAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Sentence.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Sentence() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Sentence(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Sentence(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Sentence(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: passageSet

  /** getter for passageSet - gets passages
   * @generated
   * @return value of the feature 
   */
  public FSList getPassageSet() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_passageSet == null)
      jcasType.jcas.throwFeatMissing("passageSet", "jeqa.types.Sentence");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_passageSet)));}
    
  /** setter for passageSet - sets passages 
   * @generated
   * @param v value to set into the feature 
   */
  public void setPassageSet(FSList v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_passageSet == null)
      jcasType.jcas.throwFeatMissing("passageSet", "jeqa.types.Sentence");
    jcasType.ll_cas.ll_setRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_passageSet, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: documentSet

  /** getter for documentSet - gets documents
   * @generated
   * @return value of the feature 
   */
  public FSList getDocumentSet() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_documentSet == null)
      jcasType.jcas.throwFeatMissing("documentSet", "jeqa.types.Sentence");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_documentSet)));}
    
  /** setter for documentSet - sets documents 
   * @generated
   * @param v value to set into the feature 
   */
  public void setDocumentSet(FSList v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_documentSet == null)
      jcasType.jcas.throwFeatMissing("documentSet", "jeqa.types.Sentence");
    jcasType.ll_cas.ll_setRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_documentSet, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: originalText

  /** getter for originalText - gets 正規化される前のテキスト
   * @generated
   * @return value of the feature 
   */
  public String getOriginalText() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_originalText == null)
      jcasType.jcas.throwFeatMissing("originalText", "jeqa.types.Sentence");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Sentence_Type)jcasType).casFeatCode_originalText);}
    
  /** setter for originalText - sets 正規化される前のテキスト 
   * @generated
   * @param v value to set into the feature 
   */
  public void setOriginalText(String v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_originalText == null)
      jcasType.jcas.throwFeatMissing("originalText", "jeqa.types.Sentence");
    jcasType.ll_cas.ll_setStringValue(addr, ((Sentence_Type)jcasType).casFeatCode_originalText, v);}    
   
    
  //*--------------*
  //* Feature: morphemeAnalysisList

  /** getter for morphemeAnalysisList - gets morpheme analysis result list
   * @generated
   * @return value of the feature 
   */
  public FSList getMorphemeAnalysisList() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_morphemeAnalysisList == null)
      jcasType.jcas.throwFeatMissing("morphemeAnalysisList", "jeqa.types.Sentence");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_morphemeAnalysisList)));}
    
  /** setter for morphemeAnalysisList - sets morpheme analysis result list 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMorphemeAnalysisList(FSList v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_morphemeAnalysisList == null)
      jcasType.jcas.throwFeatMissing("morphemeAnalysisList", "jeqa.types.Sentence");
    jcasType.ll_cas.ll_setRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_morphemeAnalysisList, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: cabochaAnalysisList

  /** getter for cabochaAnalysisList - gets cabocha analysis list
   * @generated
   * @return value of the feature 
   */
  public FSList getCabochaAnalysisList() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_cabochaAnalysisList == null)
      jcasType.jcas.throwFeatMissing("cabochaAnalysisList", "jeqa.types.Sentence");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_cabochaAnalysisList)));}
    
  /** setter for cabochaAnalysisList - sets cabocha analysis list 
   * @generated
   * @param v value to set into the feature 
   */
  public void setCabochaAnalysisList(FSList v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_cabochaAnalysisList == null)
      jcasType.jcas.throwFeatMissing("cabochaAnalysisList", "jeqa.types.Sentence");
    jcasType.ll_cas.ll_setRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_cabochaAnalysisList, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: ipadicChapas

  /** getter for ipadicChapas - gets ChaPASによる解析結果
   * @generated
   * @return value of the feature 
   */
  public ChaPAS getIpadicChapas() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_ipadicChapas == null)
      jcasType.jcas.throwFeatMissing("ipadicChapas", "jeqa.types.Sentence");
    return (ChaPAS)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_ipadicChapas)));}
    
  /** setter for ipadicChapas - sets ChaPASによる解析結果 
   * @generated
   * @param v value to set into the feature 
   */
  public void setIpadicChapas(ChaPAS v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_ipadicChapas == null)
      jcasType.jcas.throwFeatMissing("ipadicChapas", "jeqa.types.Sentence");
    jcasType.ll_cas.ll_setRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_ipadicChapas, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: knp

  /** getter for knp - gets KNPによる解析結果
   * @generated
   * @return value of the feature 
   */
  public KNP getKnp() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_knp == null)
      jcasType.jcas.throwFeatMissing("knp", "jeqa.types.Sentence");
    return (KNP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_knp)));}
    
  /** setter for knp - sets KNPによる解析結果 
   * @generated
   * @param v value to set into the feature 
   */
  public void setKnp(KNP v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_knp == null)
      jcasType.jcas.throwFeatMissing("knp", "jeqa.types.Sentence");
    jcasType.ll_cas.ll_setRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_knp, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: pasList

  /** getter for pasList - gets ChaPASによる述語項構造のリスト
   * @generated
   * @return value of the feature 
   */
  public FSList getPasList() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_pasList == null)
      jcasType.jcas.throwFeatMissing("pasList", "jeqa.types.Sentence");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_pasList)));}
    
  /** setter for pasList - sets ChaPASによる述語項構造のリスト 
   * @generated
   * @param v value to set into the feature 
   */
  public void setPasList(FSList v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_pasList == null)
      jcasType.jcas.throwFeatMissing("pasList", "jeqa.types.Sentence");
    jcasType.ll_cas.ll_setRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_pasList, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: candidateSentenceList

  /** getter for candidateSentenceList - gets 解候補文リスト
   * @generated
   * @return value of the feature 
   */
  public FSList getCandidateSentenceList() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_candidateSentenceList == null)
      jcasType.jcas.throwFeatMissing("candidateSentenceList", "jeqa.types.Sentence");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_candidateSentenceList)));}
    
  /** setter for candidateSentenceList - sets 解候補文リスト 
   * @generated
   * @param v value to set into the feature 
   */
  public void setCandidateSentenceList(FSList v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_candidateSentenceList == null)
      jcasType.jcas.throwFeatMissing("candidateSentenceList", "jeqa.types.Sentence");
    jcasType.ll_cas.ll_setRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_candidateSentenceList, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: containsQuery

  /** getter for containsQuery - gets queryを含むか
   * @generated
   * @return value of the feature 
   */
  public boolean getContainsQuery() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_containsQuery == null)
      jcasType.jcas.throwFeatMissing("containsQuery", "jeqa.types.Sentence");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Sentence_Type)jcasType).casFeatCode_containsQuery);}
    
  /** setter for containsQuery - sets queryを含むか 
   * @generated
   * @param v value to set into the feature 
   */
  public void setContainsQuery(boolean v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_containsQuery == null)
      jcasType.jcas.throwFeatMissing("containsQuery", "jeqa.types.Sentence");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Sentence_Type)jcasType).casFeatCode_containsQuery, v);}    
  }

    
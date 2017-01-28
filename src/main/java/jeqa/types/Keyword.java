

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;


/** Keyword
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class Keyword extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Keyword.class);
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
  protected Keyword() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Keyword(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Keyword(JCas jcas) {
    super(jcas);
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
  //* Feature: text

  /** getter for text - gets text
   * @generated
   * @return value of the feature 
   */
  public String getText() {
    if (Keyword_Type.featOkTst && ((Keyword_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "jeqa.types.Keyword");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Keyword_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets text 
   * @generated
   * @param v value to set into the feature 
   */
  public void setText(String v) {
    if (Keyword_Type.featOkTst && ((Keyword_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "jeqa.types.Keyword");
    jcasType.ll_cas.ll_setStringValue(addr, ((Keyword_Type)jcasType).casFeatCode_text, v);}    
   
    
  //*--------------*
  //* Feature: expansionSet

  /** getter for expansionSet - gets expansionSet
   * @generated
   * @return value of the feature 
   */
  public FSList getExpansionSet() {
    if (Keyword_Type.featOkTst && ((Keyword_Type)jcasType).casFeat_expansionSet == null)
      jcasType.jcas.throwFeatMissing("expansionSet", "jeqa.types.Keyword");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Keyword_Type)jcasType).casFeatCode_expansionSet)));}
    
  /** setter for expansionSet - sets expansionSet 
   * @generated
   * @param v value to set into the feature 
   */
  public void setExpansionSet(FSList v) {
    if (Keyword_Type.featOkTst && ((Keyword_Type)jcasType).casFeat_expansionSet == null)
      jcasType.jcas.throwFeatMissing("expansionSet", "jeqa.types.Keyword");
    jcasType.ll_cas.ll_setRefValue(addr, ((Keyword_Type)jcasType).casFeatCode_expansionSet, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: sentenceSet

  /** getter for sentenceSet - gets sentence set
   * @generated
   * @return value of the feature 
   */
  public FSList getSentenceSet() {
    if (Keyword_Type.featOkTst && ((Keyword_Type)jcasType).casFeat_sentenceSet == null)
      jcasType.jcas.throwFeatMissing("sentenceSet", "jeqa.types.Keyword");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Keyword_Type)jcasType).casFeatCode_sentenceSet)));}
    
  /** setter for sentenceSet - sets sentence set 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentenceSet(FSList v) {
    if (Keyword_Type.featOkTst && ((Keyword_Type)jcasType).casFeat_sentenceSet == null)
      jcasType.jcas.throwFeatMissing("sentenceSet", "jeqa.types.Keyword");
    jcasType.ll_cas.ll_setRefValue(addr, ((Keyword_Type)jcasType).casFeatCode_sentenceSet, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: passageSet

  /** getter for passageSet - gets passage set
   * @generated
   * @return value of the feature 
   */
  public FSList getPassageSet() {
    if (Keyword_Type.featOkTst && ((Keyword_Type)jcasType).casFeat_passageSet == null)
      jcasType.jcas.throwFeatMissing("passageSet", "jeqa.types.Keyword");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Keyword_Type)jcasType).casFeatCode_passageSet)));}
    
  /** setter for passageSet - sets passage set 
   * @generated
   * @param v value to set into the feature 
   */
  public void setPassageSet(FSList v) {
    if (Keyword_Type.featOkTst && ((Keyword_Type)jcasType).casFeat_passageSet == null)
      jcasType.jcas.throwFeatMissing("passageSet", "jeqa.types.Keyword");
    jcasType.ll_cas.ll_setRefValue(addr, ((Keyword_Type)jcasType).casFeatCode_passageSet, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: documentSet

  /** getter for documentSet - gets IRで取得したDocumentのセット
   * @generated
   * @return value of the feature 
   */
  public FSArray getDocumentSet() {
    if (Keyword_Type.featOkTst && ((Keyword_Type)jcasType).casFeat_documentSet == null)
      jcasType.jcas.throwFeatMissing("documentSet", "jeqa.types.Keyword");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Keyword_Type)jcasType).casFeatCode_documentSet)));}
    
  /** setter for documentSet - sets IRで取得したDocumentのセット 
   * @generated
   * @param v value to set into the feature 
   */
  public void setDocumentSet(FSArray v) {
    if (Keyword_Type.featOkTst && ((Keyword_Type)jcasType).casFeat_documentSet == null)
      jcasType.jcas.throwFeatMissing("documentSet", "jeqa.types.Keyword");
    jcasType.ll_cas.ll_setRefValue(addr, ((Keyword_Type)jcasType).casFeatCode_documentSet, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for documentSet - gets an indexed value - IRで取得したDocumentのセット
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public Document getDocumentSet(int i) {
    if (Keyword_Type.featOkTst && ((Keyword_Type)jcasType).casFeat_documentSet == null)
      jcasType.jcas.throwFeatMissing("documentSet", "jeqa.types.Keyword");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Keyword_Type)jcasType).casFeatCode_documentSet), i);
    return (Document)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Keyword_Type)jcasType).casFeatCode_documentSet), i)));}

  /** indexed setter for documentSet - sets an indexed value - IRで取得したDocumentのセット
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setDocumentSet(int i, Document v) { 
    if (Keyword_Type.featOkTst && ((Keyword_Type)jcasType).casFeat_documentSet == null)
      jcasType.jcas.throwFeatMissing("documentSet", "jeqa.types.Keyword");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Keyword_Type)jcasType).casFeatCode_documentSet), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Keyword_Type)jcasType).casFeatCode_documentSet), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: isMandatory

  /** getter for isMandatory - gets is mandatory
   * @generated
   * @return value of the feature 
   */
  public boolean getIsMandatory() {
    if (Keyword_Type.featOkTst && ((Keyword_Type)jcasType).casFeat_isMandatory == null)
      jcasType.jcas.throwFeatMissing("isMandatory", "jeqa.types.Keyword");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Keyword_Type)jcasType).casFeatCode_isMandatory);}
    
  /** setter for isMandatory - sets is mandatory 
   * @generated
   * @param v value to set into the feature 
   */
  public void setIsMandatory(boolean v) {
    if (Keyword_Type.featOkTst && ((Keyword_Type)jcasType).casFeat_isMandatory == null)
      jcasType.jcas.throwFeatMissing("isMandatory", "jeqa.types.Keyword");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Keyword_Type)jcasType).casFeatCode_isMandatory, v);}    
  }

    
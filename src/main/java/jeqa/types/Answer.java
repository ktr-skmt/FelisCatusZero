

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** 回答
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class Answer extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Answer.class);
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
  protected Answer() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Answer(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Answer(JCas jcas) {
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
  //* Feature: document

  /** getter for document - gets document
   * @generated
   * @return value of the feature 
   */
  public Document getDocument() {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_document == null)
      jcasType.jcas.throwFeatMissing("document", "jeqa.types.Answer");
    return (Document)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Answer_Type)jcasType).casFeatCode_document)));}
    
  /** setter for document - sets document 
   * @generated
   * @param v value to set into the feature 
   */
  public void setDocument(Document v) {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_document == null)
      jcasType.jcas.throwFeatMissing("document", "jeqa.types.Answer");
    jcasType.ll_cas.ll_setRefValue(addr, ((Answer_Type)jcasType).casFeatCode_document, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: isGoldStandard

  /** getter for isGoldStandard - gets 模範回答か否か
   * @generated
   * @return value of the feature 
   */
  public boolean getIsGoldStandard() {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_isGoldStandard == null)
      jcasType.jcas.throwFeatMissing("isGoldStandard", "jeqa.types.Answer");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Answer_Type)jcasType).casFeatCode_isGoldStandard);}
    
  /** setter for isGoldStandard - sets 模範回答か否か 
   * @generated
   * @param v value to set into the feature 
   */
  public void setIsGoldStandard(boolean v) {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_isGoldStandard == null)
      jcasType.jcas.throwFeatMissing("isGoldStandard", "jeqa.types.Answer");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Answer_Type)jcasType).casFeatCode_isGoldStandard, v);}    
   
    
  //*--------------*
  //* Feature: writer

  /** getter for writer - gets 作者
   * @generated
   * @return value of the feature 
   */
  public String getWriter() {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_writer == null)
      jcasType.jcas.throwFeatMissing("writer", "jeqa.types.Answer");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Answer_Type)jcasType).casFeatCode_writer);}
    
  /** setter for writer - sets 作者 
   * @generated
   * @param v value to set into the feature 
   */
  public void setWriter(String v) {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_writer == null)
      jcasType.jcas.throwFeatMissing("writer", "jeqa.types.Answer");
    jcasType.ll_cas.ll_setStringValue(addr, ((Answer_Type)jcasType).casFeatCode_writer, v);}    
  }

    
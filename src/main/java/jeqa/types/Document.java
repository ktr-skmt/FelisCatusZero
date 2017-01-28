

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;


/** IRで取得したDocument
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class Document extends TextAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Document.class);
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
  protected Document() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Document(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Document(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Document(JCas jcas, int begin, int end) {
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

  /** getter for passageSet - gets Documentが持つPassageのセット
   * @generated
   * @return value of the feature 
   */
  public FSList getPassageSet() {
    if (Document_Type.featOkTst && ((Document_Type)jcasType).casFeat_passageSet == null)
      jcasType.jcas.throwFeatMissing("passageSet", "jeqa.types.Document");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Document_Type)jcasType).casFeatCode_passageSet)));}
    
  /** setter for passageSet - sets Documentが持つPassageのセット 
   * @generated
   * @param v value to set into the feature 
   */
  public void setPassageSet(FSList v) {
    if (Document_Type.featOkTst && ((Document_Type)jcasType).casFeat_passageSet == null)
      jcasType.jcas.throwFeatMissing("passageSet", "jeqa.types.Document");
    jcasType.ll_cas.ll_setRefValue(addr, ((Document_Type)jcasType).casFeatCode_passageSet, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: sentenceSet

  /** getter for sentenceSet - gets Documentが持つSentenceのセット
   * @generated
   * @return value of the feature 
   */
  public FSList getSentenceSet() {
    if (Document_Type.featOkTst && ((Document_Type)jcasType).casFeat_sentenceSet == null)
      jcasType.jcas.throwFeatMissing("sentenceSet", "jeqa.types.Document");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Document_Type)jcasType).casFeatCode_sentenceSet)));}
    
  /** setter for sentenceSet - sets Documentが持つSentenceのセット 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentenceSet(FSList v) {
    if (Document_Type.featOkTst && ((Document_Type)jcasType).casFeat_sentenceSet == null)
      jcasType.jcas.throwFeatMissing("sentenceSet", "jeqa.types.Document");
    jcasType.ll_cas.ll_setRefValue(addr, ((Document_Type)jcasType).casFeatCode_sentenceSet, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    
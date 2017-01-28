

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.FSArray;


/** Passage
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class Passage extends TextAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Passage.class);
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
  protected Passage() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Passage(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Passage(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Passage(JCas jcas, int begin, int end) {
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
  //* Feature: documentSet

  /** getter for documentSet - gets documents
   * @generated
   * @return value of the feature 
   */
  public FSList getDocumentSet() {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_documentSet == null)
      jcasType.jcas.throwFeatMissing("documentSet", "jeqa.types.Passage");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Passage_Type)jcasType).casFeatCode_documentSet)));}
    
  /** setter for documentSet - sets documents 
   * @generated
   * @param v value to set into the feature 
   */
  public void setDocumentSet(FSList v) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_documentSet == null)
      jcasType.jcas.throwFeatMissing("documentSet", "jeqa.types.Passage");
    jcasType.ll_cas.ll_setRefValue(addr, ((Passage_Type)jcasType).casFeatCode_documentSet, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: sentenceSet

  /** getter for sentenceSet - gets Passageが持っているSentenceのセット
   * @generated
   * @return value of the feature 
   */
  public FSArray getSentenceSet() {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_sentenceSet == null)
      jcasType.jcas.throwFeatMissing("sentenceSet", "jeqa.types.Passage");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Passage_Type)jcasType).casFeatCode_sentenceSet)));}
    
  /** setter for sentenceSet - sets Passageが持っているSentenceのセット 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentenceSet(FSArray v) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_sentenceSet == null)
      jcasType.jcas.throwFeatMissing("sentenceSet", "jeqa.types.Passage");
    jcasType.ll_cas.ll_setRefValue(addr, ((Passage_Type)jcasType).casFeatCode_sentenceSet, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for sentenceSet - gets an indexed value - Passageが持っているSentenceのセット
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public Sentence getSentenceSet(int i) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_sentenceSet == null)
      jcasType.jcas.throwFeatMissing("sentenceSet", "jeqa.types.Passage");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Passage_Type)jcasType).casFeatCode_sentenceSet), i);
    return (Sentence)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Passage_Type)jcasType).casFeatCode_sentenceSet), i)));}

  /** indexed setter for sentenceSet - sets an indexed value - Passageが持っているSentenceのセット
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setSentenceSet(int i, Sentence v) { 
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_sentenceSet == null)
      jcasType.jcas.throwFeatMissing("sentenceSet", "jeqa.types.Passage");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Passage_Type)jcasType).casFeatCode_sentenceSet), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Passage_Type)jcasType).casFeatCode_sentenceSet), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: containsQuery

  /** getter for containsQuery - gets queryを含むか
   * @generated
   * @return value of the feature 
   */
  public boolean getContainsQuery() {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_containsQuery == null)
      jcasType.jcas.throwFeatMissing("containsQuery", "jeqa.types.Passage");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Passage_Type)jcasType).casFeatCode_containsQuery);}
    
  /** setter for containsQuery - sets queryを含むか 
   * @generated
   * @param v value to set into the feature 
   */
  public void setContainsQuery(boolean v) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_containsQuery == null)
      jcasType.jcas.throwFeatMissing("containsQuery", "jeqa.types.Passage");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Passage_Type)jcasType).casFeatCode_containsQuery, v);}    
  }

    
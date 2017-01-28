

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FloatArray;
import org.apache.uima.jcas.cas.TOP;


/** word embedding
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class WordEmbedding extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(WordEmbedding.class);
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
  protected WordEmbedding() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public WordEmbedding(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public WordEmbedding(JCas jcas) {
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
  //* Feature: embeddingMethod

  /** getter for embeddingMethod - gets fastText, Word2Vec, GloVe, etc.
   * @generated
   * @return value of the feature 
   */
  public String getEmbeddingMethod() {
    if (WordEmbedding_Type.featOkTst && ((WordEmbedding_Type)jcasType).casFeat_embeddingMethod == null)
      jcasType.jcas.throwFeatMissing("embeddingMethod", "jeqa.types.WordEmbedding");
    return jcasType.ll_cas.ll_getStringValue(addr, ((WordEmbedding_Type)jcasType).casFeatCode_embeddingMethod);}
    
  /** setter for embeddingMethod - sets fastText, Word2Vec, GloVe, etc. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setEmbeddingMethod(String v) {
    if (WordEmbedding_Type.featOkTst && ((WordEmbedding_Type)jcasType).casFeat_embeddingMethod == null)
      jcasType.jcas.throwFeatMissing("embeddingMethod", "jeqa.types.WordEmbedding");
    jcasType.ll_cas.ll_setStringValue(addr, ((WordEmbedding_Type)jcasType).casFeatCode_embeddingMethod, v);}    
   
    
  //*--------------*
  //* Feature: vector

  /** getter for vector - gets 
   * @generated
   * @return value of the feature 
   */
  public FloatArray getVector() {
    if (WordEmbedding_Type.featOkTst && ((WordEmbedding_Type)jcasType).casFeat_vector == null)
      jcasType.jcas.throwFeatMissing("vector", "jeqa.types.WordEmbedding");
    return (FloatArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((WordEmbedding_Type)jcasType).casFeatCode_vector)));}
    
  /** setter for vector - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setVector(FloatArray v) {
    if (WordEmbedding_Type.featOkTst && ((WordEmbedding_Type)jcasType).casFeat_vector == null)
      jcasType.jcas.throwFeatMissing("vector", "jeqa.types.WordEmbedding");
    jcasType.ll_cas.ll_setRefValue(addr, ((WordEmbedding_Type)jcasType).casFeatCode_vector, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for vector - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public float getVector(int i) {
    if (WordEmbedding_Type.featOkTst && ((WordEmbedding_Type)jcasType).casFeat_vector == null)
      jcasType.jcas.throwFeatMissing("vector", "jeqa.types.WordEmbedding");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((WordEmbedding_Type)jcasType).casFeatCode_vector), i);
    return jcasType.ll_cas.ll_getFloatArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((WordEmbedding_Type)jcasType).casFeatCode_vector), i);}

  /** indexed setter for vector - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setVector(int i, float v) { 
    if (WordEmbedding_Type.featOkTst && ((WordEmbedding_Type)jcasType).casFeat_vector == null)
      jcasType.jcas.throwFeatMissing("vector", "jeqa.types.WordEmbedding");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((WordEmbedding_Type)jcasType).casFeatCode_vector), i);
    jcasType.ll_cas.ll_setFloatArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((WordEmbedding_Type)jcasType).casFeatCode_vector), i, v);}
  }

    


/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** Bag of Words type Query
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class BoWQuery extends Query {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(BoWQuery.class);
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
  protected BoWQuery() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public BoWQuery(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public BoWQuery(JCas jcas) {
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
  //* Feature: indriQuery

  /** getter for indriQuery - gets Indri Query
   * @generated
   * @return value of the feature 
   */
  public Keyword getIndriQuery() {
    if (BoWQuery_Type.featOkTst && ((BoWQuery_Type)jcasType).casFeat_indriQuery == null)
      jcasType.jcas.throwFeatMissing("indriQuery", "jeqa.types.BoWQuery");
    return (Keyword)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((BoWQuery_Type)jcasType).casFeatCode_indriQuery)));}
    
  /** setter for indriQuery - sets Indri Query 
   * @generated
   * @param v value to set into the feature 
   */
  public void setIndriQuery(Keyword v) {
    if (BoWQuery_Type.featOkTst && ((BoWQuery_Type)jcasType).casFeat_indriQuery == null)
      jcasType.jcas.throwFeatMissing("indriQuery", "jeqa.types.BoWQuery");
    jcasType.ll_cas.ll_setRefValue(addr, ((BoWQuery_Type)jcasType).casFeatCode_indriQuery, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: algorithm

  /** getter for algorithm - gets algorithm, such as BM25, TF-IDF, etc.
   * @generated
   * @return value of the feature 
   */
  public String getAlgorithm() {
    if (BoWQuery_Type.featOkTst && ((BoWQuery_Type)jcasType).casFeat_algorithm == null)
      jcasType.jcas.throwFeatMissing("algorithm", "jeqa.types.BoWQuery");
    return jcasType.ll_cas.ll_getStringValue(addr, ((BoWQuery_Type)jcasType).casFeatCode_algorithm);}
    
  /** setter for algorithm - sets algorithm, such as BM25, TF-IDF, etc. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setAlgorithm(String v) {
    if (BoWQuery_Type.featOkTst && ((BoWQuery_Type)jcasType).casFeat_algorithm == null)
      jcasType.jcas.throwFeatMissing("algorithm", "jeqa.types.BoWQuery");
    jcasType.ll_cas.ll_setStringValue(addr, ((BoWQuery_Type)jcasType).casFeatCode_algorithm, v);}    
  }

    
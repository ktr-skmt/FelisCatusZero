

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types.ja;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.TOP;


/** 述語項構造
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class PAS extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(PAS.class);
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
  protected PAS() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public PAS(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public PAS(JCas jcas) {
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
  //* Feature: predicate

  /** getter for predicate - gets 述語文節
   * @generated
   * @return value of the feature 
   */
  public Bunsetsu getPredicate() {
    if (PAS_Type.featOkTst && ((PAS_Type)jcasType).casFeat_predicate == null)
      jcasType.jcas.throwFeatMissing("predicate", "jeqa.types.ja.PAS");
    return (Bunsetsu)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((PAS_Type)jcasType).casFeatCode_predicate)));}
    
  /** setter for predicate - sets 述語文節 
   * @generated
   * @param v value to set into the feature 
   */
  public void setPredicate(Bunsetsu v) {
    if (PAS_Type.featOkTst && ((PAS_Type)jcasType).casFeat_predicate == null)
      jcasType.jcas.throwFeatMissing("predicate", "jeqa.types.ja.PAS");
    jcasType.ll_cas.ll_setRefValue(addr, ((PAS_Type)jcasType).casFeatCode_predicate, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: caseList

  /** getter for caseList - gets 格
   * @generated
   * @return value of the feature 
   */
  public FSList getCaseList() {
    if (PAS_Type.featOkTst && ((PAS_Type)jcasType).casFeat_caseList == null)
      jcasType.jcas.throwFeatMissing("caseList", "jeqa.types.ja.PAS");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((PAS_Type)jcasType).casFeatCode_caseList)));}
    
  /** setter for caseList - sets 格 
   * @generated
   * @param v value to set into the feature 
   */
  public void setCaseList(FSList v) {
    if (PAS_Type.featOkTst && ((PAS_Type)jcasType).casFeat_caseList == null)
      jcasType.jcas.throwFeatMissing("caseList", "jeqa.types.ja.PAS");
    jcasType.ll_cas.ll_setRefValue(addr, ((PAS_Type)jcasType).casFeatCode_caseList, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    
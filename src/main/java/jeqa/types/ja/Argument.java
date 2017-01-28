

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types.ja;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** 項
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class Argument extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Argument.class);
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
  protected Argument() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Argument(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Argument(JCas jcas) {
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
  //* Feature: case

  /** getter for case - gets 格
   * @generated
   * @return value of the feature 
   */
  public String getCase() {
    if (Argument_Type.featOkTst && ((Argument_Type)jcasType).casFeat_case == null)
      jcasType.jcas.throwFeatMissing("case", "jeqa.types.ja.Argument");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Argument_Type)jcasType).casFeatCode_case);}
    
  /** setter for case - sets 格 
   * @generated
   * @param v value to set into the feature 
   */
  public void setCase(String v) {
    if (Argument_Type.featOkTst && ((Argument_Type)jcasType).casFeat_case == null)
      jcasType.jcas.throwFeatMissing("case", "jeqa.types.ja.Argument");
    jcasType.ll_cas.ll_setStringValue(addr, ((Argument_Type)jcasType).casFeatCode_case, v);}    
   
    
  //*--------------*
  //* Feature: bunsetsu

  /** getter for bunsetsu - gets 文節
   * @generated
   * @return value of the feature 
   */
  public Bunsetsu getBunsetsu() {
    if (Argument_Type.featOkTst && ((Argument_Type)jcasType).casFeat_bunsetsu == null)
      jcasType.jcas.throwFeatMissing("bunsetsu", "jeqa.types.ja.Argument");
    return (Bunsetsu)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Argument_Type)jcasType).casFeatCode_bunsetsu)));}
    
  /** setter for bunsetsu - sets 文節 
   * @generated
   * @param v value to set into the feature 
   */
  public void setBunsetsu(Bunsetsu v) {
    if (Argument_Type.featOkTst && ((Argument_Type)jcasType).casFeat_bunsetsu == null)
      jcasType.jcas.throwFeatMissing("bunsetsu", "jeqa.types.ja.Argument");
    jcasType.ll_cas.ll_setRefValue(addr, ((Argument_Type)jcasType).casFeatCode_bunsetsu, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    
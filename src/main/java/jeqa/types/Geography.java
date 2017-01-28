

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.cas.StringList;


/** 地域
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class Geography extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Geography.class);
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
  protected Geography() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Geography(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Geography(JCas jcas) {
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
  //* Feature: termList

  /** getter for termList - gets term list
   * @generated
   * @return value of the feature 
   */
  public StringList getTermList() {
    if (Geography_Type.featOkTst && ((Geography_Type)jcasType).casFeat_termList == null)
      jcasType.jcas.throwFeatMissing("termList", "jeqa.types.Geography");
    return (StringList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Geography_Type)jcasType).casFeatCode_termList)));}
    
  /** setter for termList - sets term list 
   * @generated
   * @param v value to set into the feature 
   */
  public void setTermList(StringList v) {
    if (Geography_Type.featOkTst && ((Geography_Type)jcasType).casFeat_termList == null)
      jcasType.jcas.throwFeatMissing("termList", "jeqa.types.Geography");
    jcasType.ll_cas.ll_setRefValue(addr, ((Geography_Type)jcasType).casFeatCode_termList, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: area

  /** getter for area - gets area
   * @generated
   * @return value of the feature 
   */
  public StringList getArea() {
    if (Geography_Type.featOkTst && ((Geography_Type)jcasType).casFeat_area == null)
      jcasType.jcas.throwFeatMissing("area", "jeqa.types.Geography");
    return (StringList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Geography_Type)jcasType).casFeatCode_area)));}
    
  /** setter for area - sets area 
   * @generated
   * @param v value to set into the feature 
   */
  public void setArea(StringList v) {
    if (Geography_Type.featOkTst && ((Geography_Type)jcasType).casFeat_area == null)
      jcasType.jcas.throwFeatMissing("area", "jeqa.types.Geography");
    jcasType.ll_cas.ll_setRefValue(addr, ((Geography_Type)jcasType).casFeatCode_area, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    
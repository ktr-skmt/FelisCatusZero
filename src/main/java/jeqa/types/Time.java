

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.cas.StringList;


/** 時間
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class Time extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Time.class);
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
  protected Time() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Time(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Time(JCas jcas) {
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
  //* Feature: textList

  /** getter for textList - gets texts
   * @generated
   * @return value of the feature 
   */
  public StringList getTextList() {
    if (Time_Type.featOkTst && ((Time_Type)jcasType).casFeat_textList == null)
      jcasType.jcas.throwFeatMissing("textList", "jeqa.types.Time");
    return (StringList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Time_Type)jcasType).casFeatCode_textList)));}
    
  /** setter for textList - sets texts 
   * @generated
   * @param v value to set into the feature 
   */
  public void setTextList(StringList v) {
    if (Time_Type.featOkTst && ((Time_Type)jcasType).casFeat_textList == null)
      jcasType.jcas.throwFeatMissing("textList", "jeqa.types.Time");
    jcasType.ll_cas.ll_setRefValue(addr, ((Time_Type)jcasType).casFeatCode_textList, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: year

  /** getter for year - gets year
   * @generated
   * @return value of the feature 
   */
  public int getYear() {
    if (Time_Type.featOkTst && ((Time_Type)jcasType).casFeat_year == null)
      jcasType.jcas.throwFeatMissing("year", "jeqa.types.Time");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Time_Type)jcasType).casFeatCode_year);}
    
  /** setter for year - sets year 
   * @generated
   * @param v value to set into the feature 
   */
  public void setYear(int v) {
    if (Time_Type.featOkTst && ((Time_Type)jcasType).casFeat_year == null)
      jcasType.jcas.throwFeatMissing("year", "jeqa.types.Time");
    jcasType.ll_cas.ll_setIntValue(addr, ((Time_Type)jcasType).casFeatCode_year, v);}    
  }

    
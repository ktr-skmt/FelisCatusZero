

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.cas.StringList;


/** QuestionがIRに投げたいQuery
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class Query extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Query.class);
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
  protected Query() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Query(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Query(JCas jcas) {
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
  //* Feature: alreadyFinishedRetrieving

  /** getter for alreadyFinishedRetrieving - gets is already finished retrieving by the query?
   * @generated
   * @return value of the feature 
   */
  public boolean getAlreadyFinishedRetrieving() {
    if (Query_Type.featOkTst && ((Query_Type)jcasType).casFeat_alreadyFinishedRetrieving == null)
      jcasType.jcas.throwFeatMissing("alreadyFinishedRetrieving", "jeqa.types.Query");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Query_Type)jcasType).casFeatCode_alreadyFinishedRetrieving);}
    
  /** setter for alreadyFinishedRetrieving - sets is already finished retrieving by the query? 
   * @generated
   * @param v value to set into the feature 
   */
  public void setAlreadyFinishedRetrieving(boolean v) {
    if (Query_Type.featOkTst && ((Query_Type)jcasType).casFeat_alreadyFinishedRetrieving == null)
      jcasType.jcas.throwFeatMissing("alreadyFinishedRetrieving", "jeqa.types.Query");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Query_Type)jcasType).casFeatCode_alreadyFinishedRetrieving, v);}    
   
    
  //*--------------*
  //* Feature: alreadyFinishedCorrecting

  /** getter for alreadyFinishedCorrecting - gets is already finished correcting
   * @generated
   * @return value of the feature 
   */
  public boolean getAlreadyFinishedCorrecting() {
    if (Query_Type.featOkTst && ((Query_Type)jcasType).casFeat_alreadyFinishedCorrecting == null)
      jcasType.jcas.throwFeatMissing("alreadyFinishedCorrecting", "jeqa.types.Query");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Query_Type)jcasType).casFeatCode_alreadyFinishedCorrecting);}
    
  /** setter for alreadyFinishedCorrecting - sets is already finished correcting 
   * @generated
   * @param v value to set into the feature 
   */
  public void setAlreadyFinishedCorrecting(boolean v) {
    if (Query_Type.featOkTst && ((Query_Type)jcasType).casFeat_alreadyFinishedCorrecting == null)
      jcasType.jcas.throwFeatMissing("alreadyFinishedCorrecting", "jeqa.types.Query");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Query_Type)jcasType).casFeatCode_alreadyFinishedCorrecting, v);}    
   
    
  //*--------------*
  //* Feature: knowledgeSourceList

  /** getter for knowledgeSourceList - gets knowledge source list
   * @generated
   * @return value of the feature 
   */
  public StringList getKnowledgeSourceList() {
    if (Query_Type.featOkTst && ((Query_Type)jcasType).casFeat_knowledgeSourceList == null)
      jcasType.jcas.throwFeatMissing("knowledgeSourceList", "jeqa.types.Query");
    return (StringList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Query_Type)jcasType).casFeatCode_knowledgeSourceList)));}
    
  /** setter for knowledgeSourceList - sets knowledge source list 
   * @generated
   * @param v value to set into the feature 
   */
  public void setKnowledgeSourceList(StringList v) {
    if (Query_Type.featOkTst && ((Query_Type)jcasType).casFeat_knowledgeSourceList == null)
      jcasType.jcas.throwFeatMissing("knowledgeSourceList", "jeqa.types.Query");
    jcasType.ll_cas.ll_setRefValue(addr, ((Query_Type)jcasType).casFeatCode_knowledgeSourceList, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    
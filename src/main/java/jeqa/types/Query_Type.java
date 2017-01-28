
/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.cas.TOP_Type;

/** QuestionがIRに投げたいQuery
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * @generated */
public class Query_Type extends TOP_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Query_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Query_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Query(addr, Query_Type.this);
  			   Query_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Query(addr, Query_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Query.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("jeqa.types.Query");
 
  /** @generated */
  final Feature casFeat_alreadyFinishedRetrieving;
  /** @generated */
  final int     casFeatCode_alreadyFinishedRetrieving;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getAlreadyFinishedRetrieving(int addr) {
        if (featOkTst && casFeat_alreadyFinishedRetrieving == null)
      jcas.throwFeatMissing("alreadyFinishedRetrieving", "jeqa.types.Query");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_alreadyFinishedRetrieving);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAlreadyFinishedRetrieving(int addr, boolean v) {
        if (featOkTst && casFeat_alreadyFinishedRetrieving == null)
      jcas.throwFeatMissing("alreadyFinishedRetrieving", "jeqa.types.Query");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_alreadyFinishedRetrieving, v);}
    
  
 
  /** @generated */
  final Feature casFeat_alreadyFinishedCorrecting;
  /** @generated */
  final int     casFeatCode_alreadyFinishedCorrecting;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getAlreadyFinishedCorrecting(int addr) {
        if (featOkTst && casFeat_alreadyFinishedCorrecting == null)
      jcas.throwFeatMissing("alreadyFinishedCorrecting", "jeqa.types.Query");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_alreadyFinishedCorrecting);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAlreadyFinishedCorrecting(int addr, boolean v) {
        if (featOkTst && casFeat_alreadyFinishedCorrecting == null)
      jcas.throwFeatMissing("alreadyFinishedCorrecting", "jeqa.types.Query");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_alreadyFinishedCorrecting, v);}
    
  
 
  /** @generated */
  final Feature casFeat_knowledgeSourceList;
  /** @generated */
  final int     casFeatCode_knowledgeSourceList;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getKnowledgeSourceList(int addr) {
        if (featOkTst && casFeat_knowledgeSourceList == null)
      jcas.throwFeatMissing("knowledgeSourceList", "jeqa.types.Query");
    return ll_cas.ll_getRefValue(addr, casFeatCode_knowledgeSourceList);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setKnowledgeSourceList(int addr, int v) {
        if (featOkTst && casFeat_knowledgeSourceList == null)
      jcas.throwFeatMissing("knowledgeSourceList", "jeqa.types.Query");
    ll_cas.ll_setRefValue(addr, casFeatCode_knowledgeSourceList, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Query_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_alreadyFinishedRetrieving = jcas.getRequiredFeatureDE(casType, "alreadyFinishedRetrieving", "uima.cas.Boolean", featOkTst);
    casFeatCode_alreadyFinishedRetrieving  = (null == casFeat_alreadyFinishedRetrieving) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_alreadyFinishedRetrieving).getCode();

 
    casFeat_alreadyFinishedCorrecting = jcas.getRequiredFeatureDE(casType, "alreadyFinishedCorrecting", "uima.cas.Boolean", featOkTst);
    casFeatCode_alreadyFinishedCorrecting  = (null == casFeat_alreadyFinishedCorrecting) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_alreadyFinishedCorrecting).getCode();

 
    casFeat_knowledgeSourceList = jcas.getRequiredFeatureDE(casType, "knowledgeSourceList", "uima.cas.StringList", featOkTst);
    casFeatCode_knowledgeSourceList  = (null == casFeat_knowledgeSourceList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_knowledgeSourceList).getCode();

  }
}



    
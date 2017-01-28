
/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types.ja;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** KNPによる解析結果
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * @generated */
public class KNP_Type extends DependencyAnalysis_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (KNP_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = KNP_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new KNP(addr, KNP_Type.this);
  			   KNP_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new KNP(addr, KNP_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = KNP.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("jeqa.types.ja.KNP");
 
  /** @generated */
  final Feature casFeat_analysisResultCase;
  /** @generated */
  final int     casFeatCode_analysisResultCase;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getAnalysisResultCase(int addr) {
        if (featOkTst && casFeat_analysisResultCase == null)
      jcas.throwFeatMissing("analysisResultCase", "jeqa.types.ja.KNP");
    return ll_cas.ll_getStringValue(addr, casFeatCode_analysisResultCase);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAnalysisResultCase(int addr, String v) {
        if (featOkTst && casFeat_analysisResultCase == null)
      jcas.throwFeatMissing("analysisResultCase", "jeqa.types.ja.KNP");
    ll_cas.ll_setStringValue(addr, casFeatCode_analysisResultCase, v);}
    
  
 
  /** @generated */
  final Feature casFeat_analysisResultReference;
  /** @generated */
  final int     casFeatCode_analysisResultReference;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getAnalysisResultReference(int addr) {
        if (featOkTst && casFeat_analysisResultReference == null)
      jcas.throwFeatMissing("analysisResultReference", "jeqa.types.ja.KNP");
    return ll_cas.ll_getStringValue(addr, casFeatCode_analysisResultReference);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAnalysisResultReference(int addr, String v) {
        if (featOkTst && casFeat_analysisResultReference == null)
      jcas.throwFeatMissing("analysisResultReference", "jeqa.types.ja.KNP");
    ll_cas.ll_setStringValue(addr, casFeatCode_analysisResultReference, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public KNP_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_analysisResultCase = jcas.getRequiredFeatureDE(casType, "analysisResultCase", "uima.cas.String", featOkTst);
    casFeatCode_analysisResultCase  = (null == casFeat_analysisResultCase) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_analysisResultCase).getCode();

 
    casFeat_analysisResultReference = jcas.getRequiredFeatureDE(casType, "analysisResultReference", "uima.cas.String", featOkTst);
    casFeatCode_analysisResultReference  = (null == casFeat_analysisResultReference) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_analysisResultReference).getCode();

  }
}



    
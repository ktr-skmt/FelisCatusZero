
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
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 解析結果
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * @generated */
public class Analysis_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Analysis_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Analysis_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Analysis(addr, Analysis_Type.this);
  			   Analysis_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Analysis(addr, Analysis_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Analysis.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("jeqa.types.Analysis");
 
  /** @generated */
  final Feature casFeat_analysisResult;
  /** @generated */
  final int     casFeatCode_analysisResult;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getAnalysisResult(int addr) {
        if (featOkTst && casFeat_analysisResult == null)
      jcas.throwFeatMissing("analysisResult", "jeqa.types.Analysis");
    return ll_cas.ll_getStringValue(addr, casFeatCode_analysisResult);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAnalysisResult(int addr, String v) {
        if (featOkTst && casFeat_analysisResult == null)
      jcas.throwFeatMissing("analysisResult", "jeqa.types.Analysis");
    ll_cas.ll_setStringValue(addr, casFeatCode_analysisResult, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Analysis_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_analysisResult = jcas.getRequiredFeatureDE(casType, "analysisResult", "uima.cas.String", featOkTst);
    casFeatCode_analysisResult  = (null == casFeat_analysisResult) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_analysisResult).getCode();

  }
}



    

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
import jeqa.types.Analysis_Type;

/** CaboChaやKNPの結果を保持する
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * @generated */
public class DependencyAnalysis_Type extends Analysis_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DependencyAnalysis_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DependencyAnalysis_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DependencyAnalysis(addr, DependencyAnalysis_Type.this);
  			   DependencyAnalysis_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DependencyAnalysis(addr, DependencyAnalysis_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = DependencyAnalysis.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("jeqa.types.ja.DependencyAnalysis");
 
  /** @generated */
  final Feature casFeat_analysisResultTreeFormat;
  /** @generated */
  final int     casFeatCode_analysisResultTreeFormat;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getAnalysisResultTreeFormat(int addr) {
        if (featOkTst && casFeat_analysisResultTreeFormat == null)
      jcas.throwFeatMissing("analysisResultTreeFormat", "jeqa.types.ja.DependencyAnalysis");
    return ll_cas.ll_getStringValue(addr, casFeatCode_analysisResultTreeFormat);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAnalysisResultTreeFormat(int addr, String v) {
        if (featOkTst && casFeat_analysisResultTreeFormat == null)
      jcas.throwFeatMissing("analysisResultTreeFormat", "jeqa.types.ja.DependencyAnalysis");
    ll_cas.ll_setStringValue(addr, casFeatCode_analysisResultTreeFormat, v);}
    
  
 
  /** @generated */
  final Feature casFeat_bunsetsuList;
  /** @generated */
  final int     casFeatCode_bunsetsuList;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getBunsetsuList(int addr) {
        if (featOkTst && casFeat_bunsetsuList == null)
      jcas.throwFeatMissing("bunsetsuList", "jeqa.types.ja.DependencyAnalysis");
    return ll_cas.ll_getRefValue(addr, casFeatCode_bunsetsuList);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setBunsetsuList(int addr, int v) {
        if (featOkTst && casFeat_bunsetsuList == null)
      jcas.throwFeatMissing("bunsetsuList", "jeqa.types.ja.DependencyAnalysis");
    ll_cas.ll_setRefValue(addr, casFeatCode_bunsetsuList, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public DependencyAnalysis_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_analysisResultTreeFormat = jcas.getRequiredFeatureDE(casType, "analysisResultTreeFormat", "uima.cas.String", featOkTst);
    casFeatCode_analysisResultTreeFormat  = (null == casFeat_analysisResultTreeFormat) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_analysisResultTreeFormat).getCode();

 
    casFeat_bunsetsuList = jcas.getRequiredFeatureDE(casType, "bunsetsuList", "uima.cas.FSList", featOkTst);
    casFeatCode_bunsetsuList  = (null == casFeat_bunsetsuList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_bunsetsuList).getCode();

  }
}



    
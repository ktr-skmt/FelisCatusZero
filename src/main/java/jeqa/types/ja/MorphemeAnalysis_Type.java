
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

/** MecabやJumanの結果を保持する
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * @generated */
public class MorphemeAnalysis_Type extends Analysis_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (MorphemeAnalysis_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = MorphemeAnalysis_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new MorphemeAnalysis(addr, MorphemeAnalysis_Type.this);
  			   MorphemeAnalysis_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new MorphemeAnalysis(addr, MorphemeAnalysis_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = MorphemeAnalysis.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("jeqa.types.ja.MorphemeAnalysis");
 
  /** @generated */
  final Feature casFeat_analyzer;
  /** @generated */
  final int     casFeatCode_analyzer;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getAnalyzer(int addr) {
        if (featOkTst && casFeat_analyzer == null)
      jcas.throwFeatMissing("analyzer", "jeqa.types.ja.MorphemeAnalysis");
    return ll_cas.ll_getStringValue(addr, casFeatCode_analyzer);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAnalyzer(int addr, String v) {
        if (featOkTst && casFeat_analyzer == null)
      jcas.throwFeatMissing("analyzer", "jeqa.types.ja.MorphemeAnalysis");
    ll_cas.ll_setStringValue(addr, casFeatCode_analyzer, v);}
    
  
 
  /** @generated */
  final Feature casFeat_morphemeList;
  /** @generated */
  final int     casFeatCode_morphemeList;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getMorphemeList(int addr) {
        if (featOkTst && casFeat_morphemeList == null)
      jcas.throwFeatMissing("morphemeList", "jeqa.types.ja.MorphemeAnalysis");
    return ll_cas.ll_getRefValue(addr, casFeatCode_morphemeList);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setMorphemeList(int addr, int v) {
        if (featOkTst && casFeat_morphemeList == null)
      jcas.throwFeatMissing("morphemeList", "jeqa.types.ja.MorphemeAnalysis");
    ll_cas.ll_setRefValue(addr, casFeatCode_morphemeList, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public MorphemeAnalysis_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_analyzer = jcas.getRequiredFeatureDE(casType, "analyzer", "uima.cas.String", featOkTst);
    casFeatCode_analyzer  = (null == casFeat_analyzer) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_analyzer).getCode();

 
    casFeat_morphemeList = jcas.getRequiredFeatureDE(casType, "morphemeList", "uima.cas.FSList", featOkTst);
    casFeatCode_morphemeList  = (null == casFeat_morphemeList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_morphemeList).getCode();

  }
}



    
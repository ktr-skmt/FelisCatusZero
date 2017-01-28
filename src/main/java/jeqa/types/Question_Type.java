
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

/** Examで出題されたQuestion
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * @generated */
public class Question_Type extends TOP_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Question_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Question_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Question(addr, Question_Type.this);
  			   Question_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Question(addr, Question_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Question.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("jeqa.types.Question");
 
  /** @generated */
  final Feature casFeat_xml;
  /** @generated */
  final int     casFeatCode_xml;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getXml(int addr) {
        if (featOkTst && casFeat_xml == null)
      jcas.throwFeatMissing("xml", "jeqa.types.Question");
    return ll_cas.ll_getStringValue(addr, casFeatCode_xml);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setXml(int addr, String v) {
        if (featOkTst && casFeat_xml == null)
      jcas.throwFeatMissing("xml", "jeqa.types.Question");
    ll_cas.ll_setStringValue(addr, casFeatCode_xml, v);}
    
  
 
  /** @generated */
  final Feature casFeat_document;
  /** @generated */
  final int     casFeatCode_document;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getDocument(int addr) {
        if (featOkTst && casFeat_document == null)
      jcas.throwFeatMissing("document", "jeqa.types.Question");
    return ll_cas.ll_getRefValue(addr, casFeatCode_document);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDocument(int addr, int v) {
        if (featOkTst && casFeat_document == null)
      jcas.throwFeatMissing("document", "jeqa.types.Question");
    ll_cas.ll_setRefValue(addr, casFeatCode_document, v);}
    
  
 
  /** @generated */
  final Feature casFeat_label;
  /** @generated */
  final int     casFeatCode_label;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getLabel(int addr) {
        if (featOkTst && casFeat_label == null)
      jcas.throwFeatMissing("label", "jeqa.types.Question");
    return ll_cas.ll_getStringValue(addr, casFeatCode_label);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setLabel(int addr, String v) {
        if (featOkTst && casFeat_label == null)
      jcas.throwFeatMissing("label", "jeqa.types.Question");
    ll_cas.ll_setStringValue(addr, casFeatCode_label, v);}
    
  
 
  /** @generated */
  final Feature casFeat_questionFormatType;
  /** @generated */
  final int     casFeatCode_questionFormatType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getQuestionFormatType(int addr) {
        if (featOkTst && casFeat_questionFormatType == null)
      jcas.throwFeatMissing("questionFormatType", "jeqa.types.Question");
    return ll_cas.ll_getStringValue(addr, casFeatCode_questionFormatType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQuestionFormatType(int addr, String v) {
        if (featOkTst && casFeat_questionFormatType == null)
      jcas.throwFeatMissing("questionFormatType", "jeqa.types.Question");
    ll_cas.ll_setStringValue(addr, casFeatCode_questionFormatType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_answerFormatType;
  /** @generated */
  final int     casFeatCode_answerFormatType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getAnswerFormatType(int addr) {
        if (featOkTst && casFeat_answerFormatType == null)
      jcas.throwFeatMissing("answerFormatType", "jeqa.types.Question");
    return ll_cas.ll_getStringValue(addr, casFeatCode_answerFormatType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAnswerFormatType(int addr, String v) {
        if (featOkTst && casFeat_answerFormatType == null)
      jcas.throwFeatMissing("answerFormatType", "jeqa.types.Question");
    ll_cas.ll_setStringValue(addr, casFeatCode_answerFormatType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_querySet;
  /** @generated */
  final int     casFeatCode_querySet;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getQuerySet(int addr) {
        if (featOkTst && casFeat_querySet == null)
      jcas.throwFeatMissing("querySet", "jeqa.types.Question");
    return ll_cas.ll_getRefValue(addr, casFeatCode_querySet);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQuerySet(int addr, int v) {
        if (featOkTst && casFeat_querySet == null)
      jcas.throwFeatMissing("querySet", "jeqa.types.Question");
    ll_cas.ll_setRefValue(addr, casFeatCode_querySet, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getQuerySet(int addr, int i) {
        if (featOkTst && casFeat_querySet == null)
      jcas.throwFeatMissing("querySet", "jeqa.types.Question");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_querySet), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_querySet), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_querySet), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setQuerySet(int addr, int i, int v) {
        if (featOkTst && casFeat_querySet == null)
      jcas.throwFeatMissing("querySet", "jeqa.types.Question");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_querySet), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_querySet), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_querySet), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_keywordSet;
  /** @generated */
  final int     casFeatCode_keywordSet;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getKeywordSet(int addr) {
        if (featOkTst && casFeat_keywordSet == null)
      jcas.throwFeatMissing("keywordSet", "jeqa.types.Question");
    return ll_cas.ll_getRefValue(addr, casFeatCode_keywordSet);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setKeywordSet(int addr, int v) {
        if (featOkTst && casFeat_keywordSet == null)
      jcas.throwFeatMissing("keywordSet", "jeqa.types.Question");
    ll_cas.ll_setRefValue(addr, casFeatCode_keywordSet, v);}
    
  
 
  /** @generated */
  final Feature casFeat_beginCharacterLimit;
  /** @generated */
  final int     casFeatCode_beginCharacterLimit;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getBeginCharacterLimit(int addr) {
        if (featOkTst && casFeat_beginCharacterLimit == null)
      jcas.throwFeatMissing("beginCharacterLimit", "jeqa.types.Question");
    return ll_cas.ll_getIntValue(addr, casFeatCode_beginCharacterLimit);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setBeginCharacterLimit(int addr, int v) {
        if (featOkTst && casFeat_beginCharacterLimit == null)
      jcas.throwFeatMissing("beginCharacterLimit", "jeqa.types.Question");
    ll_cas.ll_setIntValue(addr, casFeatCode_beginCharacterLimit, v);}
    
  
 
  /** @generated */
  final Feature casFeat_endCharacterLimit;
  /** @generated */
  final int     casFeatCode_endCharacterLimit;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getEndCharacterLimit(int addr) {
        if (featOkTst && casFeat_endCharacterLimit == null)
      jcas.throwFeatMissing("endCharacterLimit", "jeqa.types.Question");
    return ll_cas.ll_getIntValue(addr, casFeatCode_endCharacterLimit);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setEndCharacterLimit(int addr, int v) {
        if (featOkTst && casFeat_endCharacterLimit == null)
      jcas.throwFeatMissing("endCharacterLimit", "jeqa.types.Question");
    ll_cas.ll_setIntValue(addr, casFeatCode_endCharacterLimit, v);}
    
  
 
  /** @generated */
  final Feature casFeat_beginTimeLimit;
  /** @generated */
  final int     casFeatCode_beginTimeLimit;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getBeginTimeLimit(int addr) {
        if (featOkTst && casFeat_beginTimeLimit == null)
      jcas.throwFeatMissing("beginTimeLimit", "jeqa.types.Question");
    return ll_cas.ll_getRefValue(addr, casFeatCode_beginTimeLimit);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setBeginTimeLimit(int addr, int v) {
        if (featOkTst && casFeat_beginTimeLimit == null)
      jcas.throwFeatMissing("beginTimeLimit", "jeqa.types.Question");
    ll_cas.ll_setRefValue(addr, casFeatCode_beginTimeLimit, v);}
    
  
 
  /** @generated */
  final Feature casFeat_endTimeLimit;
  /** @generated */
  final int     casFeatCode_endTimeLimit;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getEndTimeLimit(int addr) {
        if (featOkTst && casFeat_endTimeLimit == null)
      jcas.throwFeatMissing("endTimeLimit", "jeqa.types.Question");
    return ll_cas.ll_getRefValue(addr, casFeatCode_endTimeLimit);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setEndTimeLimit(int addr, int v) {
        if (featOkTst && casFeat_endTimeLimit == null)
      jcas.throwFeatMissing("endTimeLimit", "jeqa.types.Question");
    ll_cas.ll_setRefValue(addr, casFeatCode_endTimeLimit, v);}
    
  
 
  /** @generated */
  final Feature casFeat_geographyLimit;
  /** @generated */
  final int     casFeatCode_geographyLimit;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getGeographyLimit(int addr) {
        if (featOkTst && casFeat_geographyLimit == null)
      jcas.throwFeatMissing("geographyLimit", "jeqa.types.Question");
    return ll_cas.ll_getRefValue(addr, casFeatCode_geographyLimit);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setGeographyLimit(int addr, int v) {
        if (featOkTst && casFeat_geographyLimit == null)
      jcas.throwFeatMissing("geographyLimit", "jeqa.types.Question");
    ll_cas.ll_setRefValue(addr, casFeatCode_geographyLimit, v);}
    
  
 
  /** @generated */
  final Feature casFeat_questionFocusSet;
  /** @generated */
  final int     casFeatCode_questionFocusSet;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getQuestionFocusSet(int addr) {
        if (featOkTst && casFeat_questionFocusSet == null)
      jcas.throwFeatMissing("questionFocusSet", "jeqa.types.Question");
    return ll_cas.ll_getRefValue(addr, casFeatCode_questionFocusSet);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQuestionFocusSet(int addr, int v) {
        if (featOkTst && casFeat_questionFocusSet == null)
      jcas.throwFeatMissing("questionFocusSet", "jeqa.types.Question");
    ll_cas.ll_setRefValue(addr, casFeatCode_questionFocusSet, v);}
    
  
 
  /** @generated */
  final Feature casFeat_lexicalAnswerTypeSet;
  /** @generated */
  final int     casFeatCode_lexicalAnswerTypeSet;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getLexicalAnswerTypeSet(int addr) {
        if (featOkTst && casFeat_lexicalAnswerTypeSet == null)
      jcas.throwFeatMissing("lexicalAnswerTypeSet", "jeqa.types.Question");
    return ll_cas.ll_getRefValue(addr, casFeatCode_lexicalAnswerTypeSet);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setLexicalAnswerTypeSet(int addr, int v) {
        if (featOkTst && casFeat_lexicalAnswerTypeSet == null)
      jcas.throwFeatMissing("lexicalAnswerTypeSet", "jeqa.types.Question");
    ll_cas.ll_setRefValue(addr, casFeatCode_lexicalAnswerTypeSet, v);}
    
  
 
  /** @generated */
  final Feature casFeat_semanticAnswerTypeSet;
  /** @generated */
  final int     casFeatCode_semanticAnswerTypeSet;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSemanticAnswerTypeSet(int addr) {
        if (featOkTst && casFeat_semanticAnswerTypeSet == null)
      jcas.throwFeatMissing("semanticAnswerTypeSet", "jeqa.types.Question");
    return ll_cas.ll_getRefValue(addr, casFeatCode_semanticAnswerTypeSet);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSemanticAnswerTypeSet(int addr, int v) {
        if (featOkTst && casFeat_semanticAnswerTypeSet == null)
      jcas.throwFeatMissing("semanticAnswerTypeSet", "jeqa.types.Question");
    ll_cas.ll_setRefValue(addr, casFeatCode_semanticAnswerTypeSet, v);}
    
  
 
  /** @generated */
  final Feature casFeat_answerSet;
  /** @generated */
  final int     casFeatCode_answerSet;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getAnswerSet(int addr) {
        if (featOkTst && casFeat_answerSet == null)
      jcas.throwFeatMissing("answerSet", "jeqa.types.Question");
    return ll_cas.ll_getRefValue(addr, casFeatCode_answerSet);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAnswerSet(int addr, int v) {
        if (featOkTst && casFeat_answerSet == null)
      jcas.throwFeatMissing("answerSet", "jeqa.types.Question");
    ll_cas.ll_setRefValue(addr, casFeatCode_answerSet, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Question_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_xml = jcas.getRequiredFeatureDE(casType, "xml", "uima.cas.String", featOkTst);
    casFeatCode_xml  = (null == casFeat_xml) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_xml).getCode();

 
    casFeat_document = jcas.getRequiredFeatureDE(casType, "document", "jeqa.types.Document", featOkTst);
    casFeatCode_document  = (null == casFeat_document) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_document).getCode();

 
    casFeat_label = jcas.getRequiredFeatureDE(casType, "label", "uima.cas.String", featOkTst);
    casFeatCode_label  = (null == casFeat_label) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_label).getCode();

 
    casFeat_questionFormatType = jcas.getRequiredFeatureDE(casType, "questionFormatType", "uima.cas.String", featOkTst);
    casFeatCode_questionFormatType  = (null == casFeat_questionFormatType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_questionFormatType).getCode();

 
    casFeat_answerFormatType = jcas.getRequiredFeatureDE(casType, "answerFormatType", "uima.cas.String", featOkTst);
    casFeatCode_answerFormatType  = (null == casFeat_answerFormatType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_answerFormatType).getCode();

 
    casFeat_querySet = jcas.getRequiredFeatureDE(casType, "querySet", "uima.cas.FSArray", featOkTst);
    casFeatCode_querySet  = (null == casFeat_querySet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_querySet).getCode();

 
    casFeat_keywordSet = jcas.getRequiredFeatureDE(casType, "keywordSet", "uima.cas.FSList", featOkTst);
    casFeatCode_keywordSet  = (null == casFeat_keywordSet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_keywordSet).getCode();

 
    casFeat_beginCharacterLimit = jcas.getRequiredFeatureDE(casType, "beginCharacterLimit", "uima.cas.Integer", featOkTst);
    casFeatCode_beginCharacterLimit  = (null == casFeat_beginCharacterLimit) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_beginCharacterLimit).getCode();

 
    casFeat_endCharacterLimit = jcas.getRequiredFeatureDE(casType, "endCharacterLimit", "uima.cas.Integer", featOkTst);
    casFeatCode_endCharacterLimit  = (null == casFeat_endCharacterLimit) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_endCharacterLimit).getCode();

 
    casFeat_beginTimeLimit = jcas.getRequiredFeatureDE(casType, "beginTimeLimit", "jeqa.types.Time", featOkTst);
    casFeatCode_beginTimeLimit  = (null == casFeat_beginTimeLimit) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_beginTimeLimit).getCode();

 
    casFeat_endTimeLimit = jcas.getRequiredFeatureDE(casType, "endTimeLimit", "jeqa.types.Time", featOkTst);
    casFeatCode_endTimeLimit  = (null == casFeat_endTimeLimit) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_endTimeLimit).getCode();

 
    casFeat_geographyLimit = jcas.getRequiredFeatureDE(casType, "geographyLimit", "jeqa.types.Geography", featOkTst);
    casFeatCode_geographyLimit  = (null == casFeat_geographyLimit) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_geographyLimit).getCode();

 
    casFeat_questionFocusSet = jcas.getRequiredFeatureDE(casType, "questionFocusSet", "uima.cas.StringList", featOkTst);
    casFeatCode_questionFocusSet  = (null == casFeat_questionFocusSet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_questionFocusSet).getCode();

 
    casFeat_lexicalAnswerTypeSet = jcas.getRequiredFeatureDE(casType, "lexicalAnswerTypeSet", "uima.cas.StringList", featOkTst);
    casFeatCode_lexicalAnswerTypeSet  = (null == casFeat_lexicalAnswerTypeSet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_lexicalAnswerTypeSet).getCode();

 
    casFeat_semanticAnswerTypeSet = jcas.getRequiredFeatureDE(casType, "semanticAnswerTypeSet", "uima.cas.StringList", featOkTst);
    casFeatCode_semanticAnswerTypeSet  = (null == casFeat_semanticAnswerTypeSet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_semanticAnswerTypeSet).getCode();

 
    casFeat_answerSet = jcas.getRequiredFeatureDE(casType, "answerSet", "uima.cas.FSList", featOkTst);
    casFeatCode_answerSet  = (null == casFeat_answerSet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_answerSet).getCode();

  }
}



    
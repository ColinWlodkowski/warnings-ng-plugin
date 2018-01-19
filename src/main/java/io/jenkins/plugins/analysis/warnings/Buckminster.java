package io.jenkins.plugins.analysis.warnings;

import edu.hm.hafner.analysis.IssueParser;
import edu.hm.hafner.analysis.parser.BuckminsterParser;
import io.jenkins.plugins.analysis.core.model.DefaultLabelProvider;
import io.jenkins.plugins.analysis.core.model.StaticAnalysisLabelProvider;
import io.jenkins.plugins.analysis.core.model.StaticAnalysisTool;

import hudson.Extension;

/**
 * Provides a parser and customized messages for the Buckminster Compiler.
 *
 * @author Ullrich Hafner
 */
@Extension
public class Buckminster extends StaticAnalysisTool {
    private static final String ID = "buckminster";
    private static final String PARSER_NAME = Messages.Warnings_Buckminster_ParserName();

    @Override
    public IssueParser createParser() {
return new BuckminsterParser();
}

    @Override
    public StaticAnalysisLabelProvider getLabelProvider() {
        return new LabelProvider();
    }

    /** Provides the labels for the static analysis tool. */
    private static class LabelProvider extends DefaultLabelProvider {
        private LabelProvider() {
            super(ID, PARSER_NAME);
        }
    }
}

package io.jenkins.plugins.analysis.warnings;

import edu.hm.hafner.analysis.IssueParser;
import edu.hm.hafner.analysis.parser.GnuFortranParser;
import io.jenkins.plugins.analysis.core.model.DefaultLabelProvider;
import io.jenkins.plugins.analysis.core.model.StaticAnalysisLabelProvider;
import io.jenkins.plugins.analysis.core.model.StaticAnalysisTool;

import hudson.Extension;

/**
 * Provides a parser and customized messages for the GhsFortran Compiler.
 *
 * @author Michael Schmid
 */
@Extension
public class GnuFortran extends StaticAnalysisTool {
    private static final String ID = "fortran";
    private static final String PARSER_NAME = Messages.Warnings_GnuFortran_ParserName();

    @Override
    public IssueParser createParser() {
return new GnuFortranParser();
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

package io.jenkins.plugins.analysis.core.util;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import edu.hm.hafner.analysis.IssueBuilder;
import edu.hm.hafner.analysis.Report;
import static org.assertj.core.api.Assertions.*;

import hudson.FilePath;

/**
 * Tests the class {@link AffectedFilesResolver}.
 *
 * @author Ullrich Hafner
 */
class AffectedFilesResolverTest {
    private static final FilePath BUILD_ROOT = new FilePath(new File("builds"));

    /** Ensures that illegal file names are processed without problems. */
    @ParameterizedTest(name = "[{index}] Illegal filename = {0}")
    @ValueSource(strings = {"/does/not/exist", "!<>$$&%/&(", "\0 Null-Byte"})
    void shouldReturnFallbackOnError(final String fileName) throws IOException, InterruptedException {
        Report report = new Report();
        IssueBuilder builder = new IssueBuilder();
        report.add(builder.setFileName(fileName).build());
        new AffectedFilesResolver().copyFilesWithAnnotationsToBuildFolder(
                report, BUILD_ROOT);

        assertThat(report.getErrorMessages()).hasSize(1);
        assertThat(report.getErrorMessages().get(0)).startsWith("Copying 1 affected files to Jenkins' build folder builds.");
    }
}
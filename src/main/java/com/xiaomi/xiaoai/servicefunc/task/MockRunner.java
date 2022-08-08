package com.xiaomi.xiaoai.servicefunc.task;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.FatalStartupException;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.common.SingleRootFileSource;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.standalone.CommandLineOptions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.github.tomakehurst.wiremock.core.WireMockApp.FILES_ROOT;
import static com.github.tomakehurst.wiremock.core.WireMockApp.MAPPINGS_ROOT;
import static java.lang.System.out;

@Component
public class MockRunner implements CommandLineRunner {

    private WireMockServer wireMockServer;

    private static final String BANNER =
            " /$$      /$$ /$$                     /$$      /$$                     /$$      \n"
                    + "| $$  /$ | $$|__/                    | $$$    /$$$                    | $$      \n"
                    + "| $$ /$$$| $$ /$$  /$$$$$$   /$$$$$$ | $$$$  /$$$$  /$$$$$$   /$$$$$$$| $$   /$$\n"
                    + "| $$/$$ $$ $$| $$ /$$__  $$ /$$__  $$| $$ $$/$$ $$ /$$__  $$ /$$_____/| $$  /$$/\n"
                    + "| $$$$_  $$$$| $$| $$  \\__/| $$$$$$$$| $$  $$$| $$| $$  \\ $$| $$      | $$$$$$/ \n"
                    + "| $$$/ \\  $$$| $$| $$      | $$_____/| $$\\  $ | $$| $$  | $$| $$      | $$_  $$ \n"
                    + "| $$/   \\  $$| $$| $$      |  $$$$$$$| $$ \\/  | $$|  $$$$$$/|  $$$$$$$| $$ \\  $$\n"
                    + "|__/     \\__/|__/|__/       \\_______/|__/     |__/ \\______/  \\_______/|__/  \\__/";

    @Override
    public void run(String... args) throws Exception {
        CommandLineOptions options = new CommandLineOptions(args);
        if (options.help()) {
            out.println(options.helpText());
            return;
        }

        FileSource filesRoot = options.filesRoot();
        filesRoot.createIfNecessary();
        FileSource filesFileSource = filesRoot.child(FILES_ROOT);
        filesFileSource.createIfNecessary();
        FileSource mappingsFileSource = filesRoot.child(MAPPINGS_ROOT);
        mappingsFileSource.createIfNecessary();
        options.portNumber();
        options.bindAddress();

        wireMockServer = new WireMockServer(options);
        if (!options.recordMappingsEnabled()) {
            wireMockServer.enableRecordMappings(mappingsFileSource, filesFileSource);
        }

        try {
            wireMockServer.start();
            boolean https = options.httpsSettings().enabled();

            if (!options.getHttpDisabled()) {
                options.setActualHttpPort(wireMockServer.port());
            }

            if (https) {
                options.setActualHttpsPort(wireMockServer.httpsPort());
            }

            if (!options.bannerDisabled()) {
                out.println(BANNER);
                out.println();
            } else {
                out.println();
                out.println("----------------------------------------WireMock启动成功-------------------------------------------");
            }
            out.println(options);
        } catch (FatalStartupException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}

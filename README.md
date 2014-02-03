README

Make sure java-files are compiled and that policy-file is present in bin folder before trying to run server and then client

Server:
exxon@U36SD:~/workspace/TDT4190/bin$ java -Djava.security.policy=server.policy assignment1.Server

Client:
exxon@U36SD:~/workspace/TDT4190/bin$ java -Djava.security.policy=server.policy assignment1.Client

How a policy file should look like:
grant codeBase "file:/home/exxon/workspace/TDT4190/bin/-" {
    permission java.security.AllPermission;
};

Git commands:
git add -all "folder"
git add "file"
git commit -m "Commit message"
git push origin master https://github.com/johnolos/TDT4190.git


#README

Assignment for TDT4190 writen by Markus Andersson and John-Olav Storvold.

Make sure java-files are compiled and that policy-file is present in bin folder before trying to run server and then client

Server:
exxon@U36SD:~/workspace/TDT4190/bin$ java -Djava.security.policy=server.policy assignment1.Server

Client:
exxon@U36SD:~/workspace/TDT4190/bin$ java -Djava.security.policy=server.policy assignment1.Client

How a typical policy file should look like:
grant codeBase "file:/home/exxon/workspace/TDT4190/bin/-" {
    permission java.security.AllPermission;
};

#Useful information for the group

Git commands:
git add -all "folder"
git add "file"
git commit -m "Commit message"
git push origin master https://github.com/johnolos/TDT4190.git

// First time initiating a git-repository
git init
git add --all "folder"
git add "file"
git commit -m "Commit message, first commit, fixed bug"
git push https://github.com/johnolos/TDT4190.git

// First time for another user to use git-reposity which is already created.
git clone https://github.com/johnolos/TDT4190.git
// Commands you only need to be conserned with after you cloned git-repository.
git pull https://github.com/johnolos/TDT4190.git


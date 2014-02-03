Client:
C:\Users\exxon_000\Documents\workspace\TDT4190\bin>java -Djava.security.policy=s
erver.policy assignment1.InitiateGame

Server:
C:\Users\exxon_000\Documents\workspace\TDT4190\bin>java -Djava.security.policy=s
erver.policy assignment1.Server

How a policy file should look like:
grant codeBase "file:/home/exxon/workspace/TDT4190/bin/-" {
    permission java.security.AllPermission;
};

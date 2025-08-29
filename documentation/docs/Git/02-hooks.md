# Hooks

For development include these hooks in your git repository.

`.git/hooks/pre-commit`:

```bash
#!/bin/bash
# WORKING DIR ROOT
SCRIPT_PATH="$(readlink -f "${BASH_SOURCE[0]}")"
REPO_ROOT="${SCRIPT_PATH%%.git/*}"
cd "$REPO_ROOT"

# Run Checkstyle
JAR=checkstyle-11.0.0-all.jar
mkdir -p target
STATUS=0
java -jar "$JAR" -c checkstyle.xml -f xml -o target/checkstyle-report.xml src/main/java src/test/java || STATUS=$?
# Print human-readable output to logs for convenience (do not fail this step anew)
java -jar "$JAR" -c checkstyle.xml -f plain src/main/java src/test/java || true
exit $STATUS
```

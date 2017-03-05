service "scala"

package "wget"

WORK_DIR = "~/work"
execute "mkdir \"#{WORK_DIR}\""
[
  'wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/8u121-b02/jdk-8u121-linux-x64.rpm"',
  "yum localinstall jdk-8u121-linux-x64.rpm"
].each do |command|
  execute command do
    cwd WORK_DIR
  end
end

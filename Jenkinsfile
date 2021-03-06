node('master') {
  env.JAVA_HOME = tool 'jdk8'

  env.PATH = "${env.JENKINS_HOME}/bin:${env.PATH}"
  checkout scm

  stage('Get Ansible Roles') {
    sh('#!/bin/sh -e\n' + 'rm -rf ansible/roles')
    sh('#!/bin/sh -e\n' + 'ansible-galaxy install -r ansible/requirements.yml -p ansible/roles/ -f')
  }
  stage('Build RuleChains Service') {
    sh('#!/bin/sh -e\n' + "ansible-playbook -i 'localhost,' -c local --vault-password-file=${env.DEPLOY_KEY} ansible/playbook.yml --extra-vars 'target_hosts=all java_home=${env.JAVA_HOME} deploy_env=${env.DEPLOY_ENV} package_revision=${env.BUILD_NUMBER} gather_facts=yes' -t build")
  }
  stage('Publish RuleChains Service') {
    sh('#!/bin/sh -e\n' + "ansible-playbook -i 'localhost,' -c local --vault-password-file=${env.DEPLOY_KEY} ansible/playbook.yml --extra-vars 'target_hosts=all java_home=${env.JAVA_HOME} deploy_env=${env.DEPLOY_ENV} package_revision=${env.BUILD_NUMBER} workspace=${env.WORKSPACE} bintray_api_key=${env.BINTRAY_API_KEY} gather_facts=yes' -t publish")
  }
  stage('Provision RuleChains Service') {
    sh('#!/bin/sh -e\n' + "ansible-playbook -i 'localhost,' -c local --vault-password-file=${env.DEPLOY_KEY} ansible/playbook.yml --extra-vars 'target_hosts=all java_home=${env.JAVA_HOME} deploy_env=${env.DEPLOY_ENV} package_revision=${env.BUILD_NUMBER} workspace=${env.WORKSPACE} bintray_api_key=${env.BINTRAY_API_KEY} gather_facts=yes' -t provision")
  }
  stage('Deploy RuleChains Service') {
    sshagent (credentials: ['jenkins_ymd_key']) {
      withEnv(['ANSIBLE_REMOTE_TEMP=/tmp']) {
        sh('#!/bin/sh -e\n' + "ansible-playbook -i ansible/roles/inventory/${env.DEPLOY_ENV.toLowerCase()}/hosts --user=root --vault-password-file=${env.DEPLOY_KEY} ansible/playbook.yml --extra-vars 'target_hosts=${env.DEPLOY_HOST} java_home=${env.JAVA_HOME} deploy_env=${env.DEPLOY_ENV} package_revision=${env.BUILD_NUMBER} workspace=${env.WORKSPACE} bintray_api_key=${env.BINTRAY_API_KEY} gather_facts=no' -b -t deploy -vvvv")
      }
      // sh('#!/bin/sh -e\n' + "ansible-playbook -i ansible/roles/inventory/${env.DEPLOY_ENV.toLowerCase()}/hosts --user=jenkins --vault-password-file=${env.DEPLOY_KEY} ansible/playbook.yml --extra-vars 'target_hosts=${env.DEPLOY_HOST} java_home=${env.JAVA_HOME} deploy_env=${env.DEPLOY_ENV} package_revision=${env.BUILD_NUMBER}' -b -t deploy")
    }
  }
  if( env.DEPLOY_ENV.equals("Development") ) {
    stage("Merge in UI build") {
        build job: "${env.UI_JOB_NAME}", wait: true
    }
  }
}
VAGRANTFILE_API_VERSION = "2"
vault_password_file = ENV['DEPLOY_KEY']
deploy_env = ENV['DEPLOY_ENV']
deploy_env = "Development"
vault_password_file = "/usr/local/etc/ansible/private/ymd_vault_key.txt"
ENV['ANSIBLE_REMOTE_TEMP'] = "/tmp"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  ENV['VAGRANT_DEFAULT_PROVIDER'] = 'docker'
  config.vm.box = "tknerr/baseimage-ubuntu-16.04"
  config.vm.box_version = "1.0.0"
  config.vm.hostname = "ubuntu.rulechains.dev"
  # Set the name of the VM. See: http://stackoverflow.com/a/17864388/100134
  config.vm.define "rulechainsservice" do |vm|
  end

  # config.vm.network "forwarded_port", guest: 22, host: 2201
  # config.vm.network "forwarded_port", guest: 80, host: 8001
  # config.vm.network "forwarded_port", guest: 3000, host: 3001 
  # config.ssh.username = "vagrant"
  config.vm.synced_folder ".", "/vagrant", disabled: true
  config.vm.network :private_network, type: "dhcp"
  # Configure the Docker provider for Vagrant
  config.vm.provider "docker" do |d|
    # d.image = "centos:7
    # d.image = "centos/s2i-base-centos7"
    d.name = "rulechainsservice"
    d.ports = ['2201:22','8001:80', '3001:3000']
    # d.remains_running = false
  end
  config.vm.provision :ansible do |ansible|
    ansible.playbook = "ansible/playbook.yml"
    # ansible.become = true
    ansible.verbose = "vvv"
    ansible.vault_password_file = vault_password_file
    ansible.groups = {
      "rulechains" => ["rulechainsservice"]
    }
    ansible.extra_vars = {
      # remote_user: "root",
      deploy_env: deploy_env,
      ansible_connection: "docker",
      target_hosts: "rulechains"
    }
    ansible.raw_arguments = ["--connection=docker"]
    ansible.tags = "deploy"
  end
end
# minha-bike
Projeto da Disciplina de Programação para Dispositivos Móveis I

O presente projeto tem como objetivo desenvolver um aplicativo para controlar e monitorar manutenções preventivas e custo com sua bike, com o objetivo de reduzir ou impedir falhas no desempenho da mesma.

## Deseja contribuir?

Contribuições são sempre bem vindas. Você pode contribuir de diferentes formas, disponvel a seguir. No entanto, existem algumas diretrizes que precisamos que os colaboradores sigam. 

## Formas de contribuir

1. Clicando no botão "Star" no topo da página do projeto no GitHub, nos dando mais visibilidade.
1. Relatando problemas ou solicitando recursos, por meio de uma nova issue (veja instruções a seguir). É necessário responder as perguntas do template ao criar um nova issue.
1. Participando das discurssões nas issues.
1. Melhorando a documentação do projeto.
1. Corrigindo bugs e implementando novos recursos.

As seções a seguir apresentam mais informações de como contribuir.

## Iniciando sua Contribuição

### Solicitando recurso ou relatando um problema
Se você quer solicitar um recurso ou relatar um problema, verifique primeiro se o problema/recurso que você quer reportar/requisitar já foi reportado/requisitado na página de issues. Tente pesquisar as issues existentes usando alguma palavra-chave antes de criar uma nova issue. Se não existe uma issue relacionada, sinta-se livre para criar uma. Por fim, tenha certeza de que cada issue criada esteja relacionada a um único recurso solicitado ou bug.

### Corrigindo um bug ou implementando novo recurso

1. Diretrizes de qualidade de código
O último passo antes de você iniciar o trabalho é ter em mente as seguintes diretrizes, de forma que a probabilidade de suas contribuições serem incluídas no projeto serão maiores:

- Evite duplicação de código.
- Crie funções pequenas e com uma única responsabilidade.
- Considere incluir documentação nas funções.

2. Envie suas alterações
- Atualize o branch `master` do seu fork para obter a última versão do projeto:

````bash
# Adicionar o endereço do repositório original (se ainda não fez)
git remote add upstream https://github.com/fagno/fiqueinformado-web-ifto.git

# Obter os branches remotos do respositório original
git fetch upstream

# Entrar no seu branch master local
git checkout master

# Atualizar o seu branch master com tal branch no repositório original
git merge upstream/master

# Para atualizar o seu fork no repositório caso não esteja atualizado 
git push

````

- Antes de começar a programar, você precisa primeiro fazer um fork do repositório do projeto no GitHub. Você pode corrigir um bug ou implementar um recurso de uma issue já aberta por outra pessoa ou por você mesmo, seguindo mandatoriamente os passos abaixo:

3. Crie um branch específica para trabalhar na issue

- Crie um novo branch a partir do branch `master` para incluir suas alterações. O nome de tal branch deve ter o formato `issue-NAME`
Para criar um novo branch a partir de `master`, execute: `git checkout master -b issue-NAME`. Por favor, evite fazer alterações diretamente no branch `master`.

4. Faça seus commits
- Crie commits pequenos, específicos. Assim como suas funções devem ser pequenas, seus commits devem ser focados em resolver um único problema. A resolução de uma issue normalmente pode requerer vários commits. Gaste algum tempo escrevendo mensagens de commit estruturadas, informativas e que descrevem claramente o que você fez em cada commit. 

- Execute um `rebase` para incluir suas alterações no topo do branch `master`, de forma que suas alterações serão baseadas na versão mais recente do código, antes de enviar sua contribuição. Para isto execute:

```bash
#Entrar no branch referente a issue em que estava trabalhando
git checkout issue-NAME

#Incluir as suas alterações no topo da última versão no branch master
git rebase master
```

- Faça um último commit se necessário, incluindo na primeira linha a mensagem `Close #NUMBER` para indicar o número da issue que está finalizando.
- Execute `git push` para enviar seu branch para o GitHub.
- A partir do seu fork no GitHub, abra uma Pull Request (Responda as perguntas do template).
- Aguarde suas contribuições serem avaliadas e obrigado antecipadamente pela contribuição.
